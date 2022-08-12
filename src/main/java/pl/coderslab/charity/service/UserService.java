package pl.coderslab.charity.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.User;

@Service
public interface UserService {

    User findByEmail(String email);

    boolean checkIfUserExist(String email);

    void saveUser(User user);

    void editUser(User user);

    User showUserToEdit(UserDetails userDetails);

    User currentUserEdit(UserDetails userDetails, User user, String passwordToChange);

    void addUserByAdmin(User user);

    void addAdmin(User user);

    void addUser(User user);

    void deleteUser(Long id);

    void blockUser(Long id);
}
