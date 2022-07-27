package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class RegisterController {
    private final UserService userService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "/login/register";
    }

    @PostMapping("/register")
    public String register(@Valid User user, BindingResult result, @RequestParam String matchingPassword) {
        if(!user.getPassword().equals(matchingPassword)) {
            return "redirect:/register/error";
        }
        if(result.hasErrors()) {
            return "/register";
        }
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/register/error")
    public String registerError(Model model) {
        model.addAttribute("user",new User());
        model.addAttribute("error", "error");
        return "/login/register";
    }
}
