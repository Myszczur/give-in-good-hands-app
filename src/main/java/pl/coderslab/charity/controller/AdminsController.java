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
@RequestMapping("/admin/admins")
@AllArgsConstructor
public class AdminsController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @GetMapping("")
    public String adminList(Model model) {
        model.addAttribute("adminsList", userRepository.findAllByRolesOrderByEmail(roleRepository.getById(2)));
        return "/admin/admins/list";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam Long id, Model model) {
        model.addAttribute("adminById", userRepository.getById(id));
        return "/admin/admins/edit";
    }

    @PostMapping(value = "/edit")
    public String edit(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "/admin/admins/edit";
        }
        userRepository.save(user);
        return "redirect:/admin/admins";
    }

    @GetMapping("/add")
    public String add( Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("users", userRepository.findAllByRolesOrderByEmail(roleRepository.getById(1)));
        return "/admin/admins/add";
    }

    @PostMapping(value = "/add")
    public String add(User user, BindingResult result) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getById(2));
        user.setRoles(roles);
        userRepository.save(user);
        return "redirect:/admin/admins";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute("AdminById", userRepository.getById(id));
        return "/admin/admins/deleteConfirmation";
    }

    @GetMapping(value = "/delete")
    public String delete(@RequestParam Long id) {
        userRepository.deleteById(id);
        return "redirect:/admin/admins";
    }


}
