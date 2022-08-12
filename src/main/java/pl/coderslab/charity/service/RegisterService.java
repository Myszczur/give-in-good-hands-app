package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.User;

import javax.validation.Valid;

@Service
public interface RegisterService {
    void sendRegisterEmail(String email, @Valid User user);

    void registerToken(String user);

    boolean tokenExpired(String token);
}
