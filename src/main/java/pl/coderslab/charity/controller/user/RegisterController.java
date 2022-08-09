package pl.coderslab.charity.controller.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.email.EmailService;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.model.VerificationToken;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.repository.VerificationTokenRepository;
import pl.coderslab.charity.service.UserService;
import pl.coderslab.charity.service.VerificationTokenService;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@AllArgsConstructor
public class RegisterController {
    private final UserService userService;
    private final EmailService emailService;
    private final VerificationTokenService tokenService;
    private final VerificationTokenRepository tokenRepository;
    private final UserRepository userRepository;


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
        VerificationToken token = tokenService.createToken(user);
        userService.saveUser(user);
//        String subject = "Potwierdż sówj adres email:";
//        String text = "Link: " + "http://localhost:8080/register/" + token.getToken();
//        emailService.sendSimpleMessage(user.getEmail(), subject, text);
        return "redirect:/login/tokenEnabled";
    }

    @GetMapping("/register/{token}")
    public String registerToken(@PathVariable String token) {
        VerificationToken verificationToken = tokenRepository.findByToken(token);
        if (LocalDateTime.now().isBefore(LocalDateTime.parse(verificationToken.getExpiryDate()))) {
            User user = verificationToken.getUser();
            user.setEnabled(true);
            userRepository.save(user);
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
