package pl.coderslab.charity.controller.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.service.RegisterService;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class RegisterController {
    private final UserService userService;
    private final RegisterService registerService;


    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "/login/register";
    }

    @PostMapping("/register")
    public String register(@Valid User user, BindingResult result, @RequestParam String matchingPassword) {
        if (!user.getPassword().equals(matchingPassword)) {
            return "redirect:/register/error";
        }
        if (result.hasErrors()) {
            return "/login/register";
        }
        userService.saveUser(user);
        registerService.sendRegisterEmail(user.getEmail(), user);
        return "redirect:/login/tokenEnabled";
    }

    @GetMapping("/register/{token}")
    public String registerToken(@PathVariable String token) {
        if (registerService.tokenExpired(token)) {
            registerService.registerToken(token);
            return "redirect:/login/enabled";
        }
        return "redirect:/login/noEnabled";
    }

    @GetMapping("/register/error")
    public String registerError(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("error", "error");
        return "/login/register";
    }
}
