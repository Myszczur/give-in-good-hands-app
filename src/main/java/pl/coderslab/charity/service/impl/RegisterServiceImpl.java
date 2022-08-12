package pl.coderslab.charity.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.email.EmailService;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.model.VerificationToken;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.repository.VerificationTokenRepository;
import pl.coderslab.charity.service.RegisterService;
import pl.coderslab.charity.service.VerificationTokenService;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    private final EmailService emailService;
    private final VerificationTokenService tokenService;
    private final UserRepository userRepository;
    private final VerificationTokenRepository tokenRepository;


    @Override
    public void sendRegisterEmail(String email, @Valid User user) {
        VerificationToken token = tokenService.createToken(user);
        String subject = "Potwierdż sówj adres email:";
        String text = "Link: " + "http://localhost:8080/register/" + token.getToken();
        emailService.sendSimpleMessage(user.getEmail(), subject, text);
    }

    @Override
    public boolean tokenExpired(String token) {
        VerificationToken verificationToken = tokenRepository.findByToken(token);
        return LocalDateTime.now().isBefore(LocalDateTime.parse(verificationToken.getExpiryDate()));
    }

    @Override
    public void registerToken(String token) {
        VerificationToken verificationToken = tokenRepository.findByToken(token);
        User user = verificationToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);
    }
}
