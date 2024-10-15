package com.kemal.spring.web.controllers.viewControllers.adminControllers;

import com.kemal.spring.domain.User;
import com.kemal.spring.domain.UserRepository;
import com.kemal.spring.domain.administrative.ResetPassword;
import com.kemal.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ResetPasswordController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/password-reset")
    public String doPasswordReset(Model model) {
        System.out.println("redirect successfully");
        model.addAttribute("resetPassword", new ResetPassword());
        List<String> emailList=new ArrayList<>();
        emailList.add("Select user email");
        emailList.addAll(userRepository.findAllEmailUser());
        model.addAttribute("emailList",emailList);// Make sure you have a ResetPasswordDto class
        return "users/resetpassword";

    }


    @PostMapping("/password-reset")
    String updatePassword(@ModelAttribute("resetPassword") ResetPassword resetPassword, Model model, RedirectAttributes redirectAttributes) {
        try {
            System.out.println("user" + resetPassword.getEmail());
            User user = userService.findByEmail(resetPassword.getEmail());
            System.out.println("user" + user);
            if (user != null) {
                if (!resetPassword.getConfirmPassword().equals(resetPassword.getNewPassword())) {
                    redirectAttributes.addFlashAttribute("userNotExist", "New password and confirm password should be same.");

                } else {
                    user.setPassword(passwordEncoder.encode(resetPassword.getNewPassword()));
                    userRepository.save(user);
                    redirectAttributes.addFlashAttribute("passwordUpdated", "Password updated successfully");
                }

            } else {
                redirectAttributes.addFlashAttribute("userNotExist", "Email does not exist.");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("userNotExist", "Please try again.");

        }
        System.out.println("user password updated");
        return "redirect:/password-reset";
    }

}
