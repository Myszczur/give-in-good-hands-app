package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.email.EmailService;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.model.VerificationToken;
import pl.coderslab.charity.repository.VerificationTokenRepository;
import pl.coderslab.charity.service.UserService;
import pl.coderslab.charity.service.VerificationTokenService;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/reset")
@AllArgsConstructor
public class PasswordController {
    private final UserService userService;
    private final VerificationTokenService verificationTokenService;
    private final EmailService emailService;
    private final VerificationTokenRepository verificationTokenRepository;

    @GetMapping("")
    public String resetPassword() {
        return "/login/reset";
    }

    @PostMapping("")
    public String resetPassword(@RequestParam String email) {
        if (userService.checkIfUserExist(email)) {

//            User user = userService.findByEmail(email);
//            VerificationToken verificationToken = verificationTokenService.createToken(user);
//            String subject = "Reset hasła";
//            String text = "Link: " + "http://localhost:8082/password/" + verificationToken.getToken();
//            emailService.sendSimpleMessage(email, subject, text);
            return "redirect:/login/password";
        }
        return "redirect:/login/noExist";
    }

    @GetMapping("/password")
    public String resetPasswordForm(@RequestParam String token, Model model) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);

        if (LocalDateTime.now().isBefore(LocalDateTime.parse(verificationToken.getExpiryDate()))) {
            model.addAttribute("user", new User());
            model.addAttribute("token", verificationToken);
            return "/login/password";
        }
        return "redirect:/login/noEnabled";
    }

    @PostMapping("/password/{token}")
    public String resetPasswordForm(@Valid User user, BindingResult result, @PathVariable String token, Model model, @RequestParam String checkPassword) {

        if (!user.getPassword().equals(checkPassword)) {
            model.addAttribute("error", "error");
            return "redirect:/password/error/";
        }
        if (result.hasErrors()) {
            return "/login/password";
        }

        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        User userPasswordChange = verificationToken.getUser();
        userPasswordChange.setPassword(user.getPassword());
        userService.editUser(userPasswordChange);
        return "redirect:/login/success";
    }
}
