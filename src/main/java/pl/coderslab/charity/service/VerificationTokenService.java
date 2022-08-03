package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.model.VerificationToken;

@Service
public interface VerificationTokenService {
    VerificationToken createToken(User user);
}
