package com.works.controllers;

import com.works.entities.UserModel;
import com.works.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class LoginController {

    final UserService userService;
    String csrf = "";

    @GetMapping("/login")
    public String login(Model model) {
        csrf = UUID.randomUUID().toString();
        model.addAttribute("csrf", csrf);
        return "login";
    }

    @PostMapping("/userLogin")
    public String userLogin(UserModel userModel, @RequestParam String csrf) {
        if ( this.csrf.equals(csrf) ) {
            boolean status = userService.login(userModel);
            if (status)
                return "redirect:/dashboard";
            return "redirect:/login";
        }else {
            this.csrf = "";
            return "redirect:/login";
        }
    }

}
