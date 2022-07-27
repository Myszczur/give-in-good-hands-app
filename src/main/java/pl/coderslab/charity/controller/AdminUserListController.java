package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.Role;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin/users")
@AllArgsConstructor
public class AdminUserListController {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;


    @GetMapping("")
    public String usersList(Model model) {
        model.addAttribute("userList", userRepository.findAllByRolesOrderByEmail(roleRepository.getById(1)));
        return "/admin/users/userslist";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam Long id, Model model) {
        model.addAttribute("adminById", userRepository.getById(id));
        return "/admin/users/edit";
    }

    @PostMapping(value = "/edit")
    public String edit(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "/admin/users/edit";
        }
        userRepository.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/add")
    public String addAdmin(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("users", userRepository.findAllByRolesOrderByEmail(roleRepository.getById(1)));
        return "/admin/users/add";
    }

    @PostMapping(value = "/add")
    public String addAdmin(User user, BindingResult result) {
        if (result.hasErrors()) {
            return "/admin/users/edit";
        }
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getById(1));
        user.setRoles(roles);
        userRepository.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/adduser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "/admin/users/adduser";
    }

    @PostMapping(value = "/adduser")
    public String addUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "/admin/users/adduser";
        }
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getById(1));
        user.setRoles(roles);
        userRepository.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute("AdminById", userRepository.getById(id));
        return "/admin/users/deleteConfirmation";
    }

    @GetMapping(value = "/delete")
    public String delete(@RequestParam Long id) {
        userRepository.deleteById(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/block")
    public String block(@RequestParam Long id) {
        User userToBlock = userRepository.getById(id);
        userToBlock.setAccountNonBlocked(!userToBlock.isAccountNonBlocked());
        userRepository.save(userToBlock);
        return "redirect:/admin/users";
    }


}
