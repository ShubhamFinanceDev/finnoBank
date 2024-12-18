package com.kemal.spring.web.controllers.viewControllers.adminControllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kemal.spring.domain.User;
import com.kemal.spring.service.UserService;
import com.kemal.spring.web.dto.UserDto;

/**
 * Created by Keno&Kemo on 20.11.2017..
 */
@Controller
@RequestMapping("")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ModelAndView showRoles() {
        ModelAndView modelAndView = new ModelAndView("users/usercreation");
        modelAndView.addObject("users", userService.findAll());
        return modelAndView;
    }

    @GetMapping("/users/{id}")
    public ModelAndView getEditUserForm(@PathVariable Long id) {
        Optional<User> role = userService.findById(id);
        ModelAndView modelAndView = new ModelAndView("users/usercreation");
        modelAndView.addObject("userDto", role.get());
        return modelAndView;
    }

    

    @GetMapping("/users/newUser")
    public String getAddNewRoleForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        model.addAttribute("users", userService.findAll());
        return "users/usercreation";
    }

    @PostMapping("/users/newUser")
    public String saveNewRole(@ModelAttribute("userDto") @Valid final UserDto userDto,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        boolean roleNameAlreadyExists = userService.findByName(userDto.getEmail().split("@")[0]) != null;
        boolean hasErrors = roleNameAlreadyExists || bindingResult.hasErrors();
        String formWithErrors = "users/usercreation";

        if (roleNameAlreadyExists) bindingResult.rejectValue("name", "userNameAlreadyExists");
        if (hasErrors) return formWithErrors;

        userService.createNewAccount(userDto);
        redirectAttributes.addFlashAttribute("userHasBeenSaved", true);
        
        return "redirect:newUser";
    }
}
