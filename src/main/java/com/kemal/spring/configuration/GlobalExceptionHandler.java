package com.kemal.spring.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@Configuration
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleException(Exception ex, Model model) {
        model.addAttribute("title", "Error Occurred");
        model.addAttribute("errorMessageTitle", "Internal Server Error");
        model.addAttribute("errorMessageBody", ex.getMessage());
        return new ModelAndView("error");
    }

    // Handle 404 error
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handle404(NoHandlerFoundException ex, Model model) {
        model.addAttribute("title", "Page Not Found");
        model.addAttribute("errorMessageTitle", "404 Not Found");
        model.addAttribute("errorMessageBody", "We are sorry, but the page you requested was not found.");
        return new ModelAndView("error");
    }

    // Handle Bad Credentials error
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ModelAndView handleBadCredentials(BadCredentialsException ex, Model model) {
        model.addAttribute("title", "Authentication Error");
        model.addAttribute("errorMessageTitle", "Bad Credentials");
        model.addAttribute("errorMessageBody", "The credentials you provided are incorrect.");
        return new ModelAndView("error");
    }
}
