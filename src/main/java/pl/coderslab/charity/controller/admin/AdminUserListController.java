package pl.coderslab.charity.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/users")
@AllArgsConstructor
public class AdminUserListController {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserService userService;


    @GetMapping("")
    public String usersList(Model model) {
        model.addAttribute("usersList", userRepository.findAllByRolesOrderByEmail(roleRepository.getById(4)));
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
        model.addAttribute("users", userRepository.findAllByRolesOrderByEmail(roleRepository.getById(4)));
        return "/admin/users/add";
    }

    @PostMapping(value = "/add")
    public String addAdmin(User user, BindingResult result) {
        if (result.hasErrors()) {
            return "/admin/users/edit";
        }
        userService.addAdmin(user);
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
        userService.addUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute("userById", userRepository.getById(id));
        return "/admin/users/deleteConfirmation";
    }

    @GetMapping(value = "/delete")
    public String delete(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/block")
    public String block(@RequestParam Long id) {
        userService.blockUser(id);
        return "redirect:/admin/users";
    }
}
