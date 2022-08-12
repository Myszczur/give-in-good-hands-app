package pl.coderslab.charity.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.email.EmailService;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.model.VerificationToken;
import pl.coderslab.charity.repository.VerificationTokenRepository;
import pl.coderslab.charity.service.PasswordService;
import pl.coderslab.charity.service.UserService;
import pl.coderslab.charity.service.VerificationTokenService;

@Service
@RequiredArgsConstructor
public class PasswordServiceImpl implements PasswordService {

    private final UserService userService;
    private final VerificationTokenRepository verificationTokenRepository;
    private final VerificationTokenService verificationTokenService;
    private final EmailService emailService;

    @Override
    public void resetPasswordForm(User user, String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        User userPasswordChange = verificationToken.getUser();
        userPasswordChange.setPassword(user.getPassword());
        userService.editUser(user);

    }

    @Override
    public void sendResetPasswordEmail(String email) {
        User user = userService.findByEmail(email);
        VerificationToken verificationToken = verificationTokenService.createToken(user);
        String subject = "Reset hasła";
        String text = "Link: " + "http://localhost:8082/password/" + verificationToken.getToken();
        emailService.sendSimpleMessage(email, subject, text);

    }
}
