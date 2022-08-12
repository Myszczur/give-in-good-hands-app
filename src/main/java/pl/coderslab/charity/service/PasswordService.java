package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.User;

@Service
public interface PasswordService {

    void resetPasswordForm(User user, String token);

    void sendResetPasswordEmail(String email);

}
