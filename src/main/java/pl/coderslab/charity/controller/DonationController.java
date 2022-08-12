package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@AllArgsConstructor
@RequestMapping("/donations")
public class DonationController {

    private final DonationService donationService;
    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;
    private final UserService userService;


    @GetMapping("/add")
    public String addDonation(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("institutions", institutionRepository.findAll());
        model.addAttribute("donation", new Donation());
        return "/donations/add";
    }


    @PostMapping("/add")
    public String addDonations(@AuthenticationPrincipal UserDetails userDetails, Donation donation) {
        User user = userService.findByEmail(userDetails.getUsername());
        donationService.addDonations(donation, user);
        return "donations/form-confirmation";
    }

}
