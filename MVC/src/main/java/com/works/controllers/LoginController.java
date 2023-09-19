package com.works.controllers;

import com.works.entities.UserModel;
import com.works.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/userLogin")
    public String userLogin(UserModel userModel) {
        boolean status = userService.login(userModel);
        if(status)
            return "redirect:/dashboard";
        return "login";
    }

}
