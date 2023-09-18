package com.works.controllers;

import com.works.entities.User;
import com.works.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class SingUpController {

    final UserService userService;


    @GetMapping("/")
    public String home() {
        return "signup";
    }

    @PostMapping("/signup")
    public String singup(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors() ) {
            // result.getFieldErrors().get(0).getDefaultMessage();
            // result.getFieldErrors().get(0).getField()
            model.addAttribute("errors", result.getFieldErrors());
            return "signup";
        }
        boolean status = userService.singup(user);
        if ( status ) {
            model.addAttribute("email", user.getEmail());
            System.out.println( user );
        }else {
            System.out.println("400 Bad Request");
        }
        return "signup";
    }


}
