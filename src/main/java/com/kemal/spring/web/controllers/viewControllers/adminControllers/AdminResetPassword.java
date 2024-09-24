package com.kemal.spring.web.controllers.viewControllers.adminControllers;

import com.kemal.spring.domain.User;
import com.kemal.spring.domain.UserRepository;
import com.kemal.spring.domain.administrative.ResetPassword;
import com.kemal.spring.service.UserService;
import com.kemal.spring.service.userDetails.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminResetPassword {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/password-reset")
    public String passwordReset(Model model) {
        model.addAttribute("resetPassword", new ResetPassword()); // Make sure you have a ResetPasswordDto class

        return "website/resetpassword";
    }

    @PostMapping("/update-password")
    String updatePassword(@ModelAttribute("resetPassword") ResetPassword resetPassword, Model model) {
        try {
            User user = userService.findByEmail(resetPassword.getEmail());
            System.out.println("user" + user);
            if (user != null) {
                if (!resetPassword.getConfirmPassword().equals(resetPassword.getNewPassword())) {
                    model.addAttribute("msg", "New password and confirm password should be same.");

                } else {
                    user.setPassword(passwordEncoder.encode(resetPassword.getNewPassword()));
                    userRepository.save(user);
                    model.addAttribute("msg", "Password updated");
                }

            } else {
                model.addAttribute("msg", "Email does not exist.");
            }
        } catch (Exception e) {
            model.addAttribute("msg", "Please try again.");

        }

        return "website/resetpassword";
    }
}
