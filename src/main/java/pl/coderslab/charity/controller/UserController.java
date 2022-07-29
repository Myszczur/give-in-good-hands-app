package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;
import java.util.Collections;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final DonationRepository donationRepository;


    @GetMapping("")
    public String userPanel(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User currentUser = userService.findByEmail(userDetails.getUsername());
        model.addAttribute("currentUserDonations",
                donationRepository.findAllByUserIdOrderByStatusDescReceivedDescCreatedDesc(currentUser.getId()));
        return "/user/user";
    }

    @GetMapping("/edit")
    public String edit(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User currentUser = userService.findByEmail(userDetails.getUsername());
        currentUser.setPassword("");
        model.addAttribute("user", currentUser);

        return "/user/edit";
    }

    @PostMapping(value = "/edit")
    public String edit(@Valid User user, BindingResult result, @AuthenticationPrincipal UserDetails userDetails, @RequestParam String passwordToChange, Model model) {
        User currentUserEdit = userService.findByEmail(userDetails.getUsername());
        if (result.hasErrors()) {
            currentUserEdit.setPassword("");
            model.addAttribute("user", currentUserEdit);
            return "/user/edit";
        }
        if (!passwordToChange.isEmpty()) {
            currentUserEdit.setPassword(passwordToChange);
        }
        currentUserEdit.setEmail(user.getEmail());
        currentUserEdit.setFirstName(user.getFirstName());
        currentUserEdit.setLastName(user.getLastName());
        userService.editUser(currentUserEdit);
        return "redirect:/user";
    }


}
