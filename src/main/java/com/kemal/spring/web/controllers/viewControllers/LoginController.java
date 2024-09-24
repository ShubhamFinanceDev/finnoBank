package com.kemal.spring.web.controllers.viewControllers;

import com.kemal.spring.domain.administrative.ResetPassword;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Keno&Kemo on 17.11.2017..
 */

@Controller
@RequestMapping("")
public class LoginController {

    // Login form with error
    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        model.addAttribute("msg","Bad credentials");
        return "website/login";
    }
}
