package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.User;

@Service
public interface UserService {

    User findByEmail(String email);

    void saveUser(User user);
}
