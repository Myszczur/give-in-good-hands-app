package pl.coderslab.charity.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Role;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserService userService;


    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean checkIfUserExist(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public void editUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User showUserToEdit(UserDetails userDetails) {
        User currentUser = userService.findByEmail(userDetails.getUsername());
        currentUser.setPassword("");
        return currentUser;
    }

    @Override
    public User currentUserEdit(UserDetails userDetails, User user, String passwordToChange) {
        User currentUserEdit = userService.findByEmail(userDetails.getUsername());
        if (!passwordToChange.isEmpty()) {
            currentUserEdit.setPassword(passwordToChange);
        }
        currentUserEdit.setEmail(user.getEmail());
        currentUserEdit.setFirstName(user.getFirstName());
        currentUserEdit.setLastName(user.getLastName());
        return currentUserEdit;
    }

    @Override
    public void addUserByAdmin(User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getById(2));
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public void addAdmin(User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getById(2));
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public void addUser(User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getById(4));
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User userToDelete = userRepository.getOne(id);
        userToDelete.setRoles(null);
        userRepository.save(userToDelete);
        userRepository.delete(userToDelete);
    }

    @Override
    public void blockUser(Long id) {
        User userToBlock = userRepository.getById(id);
        userToBlock.setAccountNonLocked(!userToBlock.isAccountNonLocked());
        userRepository.save(userToBlock);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(false);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<>(List.of(userRole)));
        userRepository.save(user);
    }
}
