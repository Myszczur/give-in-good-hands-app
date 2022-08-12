package pl.coderslab.charity.controller.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final DonationRepository donationRepository;
    private final DonationService donationService;


    @GetMapping("")
    public String userPanel(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("currentUserDonations", donationService.findAllDonationByUser(userDetails));
        return "/user/user";
    }

    @GetMapping("/edit")
    public String edit(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("user", userService.showUserToEdit(userDetails));
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
        userService.editUser(userService.currentUserEdit(userDetails, user, passwordToChange));
        return "redirect:/user";
    }

    @GetMapping("/show/{id}")
    public String userDonationDetails(Model model, @PathVariable Long id) {
        model.addAttribute("donations", donationRepository.getOne(id));
        return "/user/show";
    }

    @GetMapping("/received/{id}")
    public String userDonationReceived(Model model, @PathVariable Long id) {
        donationService.updateDonation(id);
        model.addAttribute("donations", donationRepository.getOne(id));
        return "/user/show";
    }
}
