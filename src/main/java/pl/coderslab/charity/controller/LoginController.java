package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class LoginController {


    @GetMapping("/login")
    public String login() {
        return "/login/login";
    }

    @GetMapping("/login/{error}")
    public String loginError(@PathVariable String error, Model model) {
        model.addAttribute("error", error);
        return "/login/login";
    }
}
