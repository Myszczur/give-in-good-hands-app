package pl.coderslab.charity;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.Institution.InstitutionRepository;
import pl.coderslab.charity.donation.DonationRepository;


@Controller
@AllArgsConstructor
public class HomeController {
    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;

    @RequestMapping("/")
    public String homeAction(Model model) {
        model.addAttribute("foundations", institutionRepository.findAll());
        model.addAttribute("donatedBags", donationRepository.sumDonatedBags());
        model.addAttribute("donatedCategory", donationRepository.countDonatedCategory());
        return "index";
    }
}
