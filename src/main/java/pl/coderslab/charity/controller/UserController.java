package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final BCryptPasswordEncoder passwordEncoder;


    @GetMapping("")
    public String userPanel(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        //TODO: Zrobić listę donacji w panelu usera
        User currentUser = userService.findByEmail(userDetails.getUsername());
        model.addAttribute("currentUserDonations", donationRepository.findAllById(Collections.singleton(currentUser.getId())));
        return "/user/user";
    }

    @GetMapping("/edit")
    public String edit(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        //TODO: odkodowanie password z bazy dany i wyświetlić w widoku
        User currentUser = userService.findByEmail(userDetails.getUsername());
//        currentUser.setPassword(passwordEncoder.(currentUser.getPassword()));
        model.addAttribute("currentUser", currentUser);
        return "/user/edit";
    }

    @PostMapping(value = "/edit")
    public String edit(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "/user/edit";
        }
        userRepository.save(user);
        return "redirect:/user";
    }


}
