package pl.coderslab.charity.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.model.VerificationToken;
import pl.coderslab.charity.repository.VerificationTokenRepository;
import pl.coderslab.charity.service.VerificationTokenService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@AllArgsConstructor
public class VerificationTokenServiceImpl implements VerificationTokenService {
    
    private static final int EXPIRATION = 60 * 24;
    private final VerificationTokenRepository tokenRepository;

    private String calculateExpiryDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().plusMinutes(VerificationTokenServiceImpl.EXPIRATION).format(formatter);
    }

    @Override
    public VerificationToken createToken(User user) {
        VerificationToken token = new VerificationToken();
        if(tokenRepository.findByUser(user) != null) {
            token.setId(tokenRepository.findByUser(user).getId());
        }
        String tokenStr = UUID.randomUUID().toString();
        token.setToken(tokenStr);
        token.setExpiryDate(calculateExpiryDate());
        token.setUser(user);
        tokenRepository.save(token);
        return token;
    }
}
