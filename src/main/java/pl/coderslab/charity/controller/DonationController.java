package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.model.Donation;

@Controller
@AllArgsConstructor
@RequestMapping("/donations")
public class DonationController {

    private final DonationRepository donationRepository;
    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;


    @GetMapping("/add")
    public String addDonation(Model model) {

        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("institutions", institutionRepository.findAll());
        model.addAttribute("donation", new Donation());
        return "/donations/add";
    }


    @PostMapping("/add")
    public String addDonations( Donation donation) {

        donationRepository.save(donation);
        return "donations/form-confirmation";
    }

}