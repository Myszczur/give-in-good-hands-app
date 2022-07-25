package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.repository.DonationRepository;


@Controller
@AllArgsConstructor
public class HomeController {
    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;

    @RequestMapping("/")
    public String homeAction(Model model) {
        if (donationRepository.sumDonatedBags() == null) {
            model.addAttribute("donatedBags", "0");
        } else {
            model.addAttribute("donatedBags", donationRepository.sumDonatedBags());
        }
        model.addAttribute("foundations", institutionRepository.findAll());
        model.addAttribute("donatedCategory", donationRepository.countDonatedCategory());
        return "index";
    }
}
