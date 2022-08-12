package pl.coderslab.charity.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.User;

import java.util.List;

@Service
public interface DonationService {

    void addDonations(Donation donation, User user);

    List<Donation> findAllDonationByUser(UserDetails userDetails);

    void updateDonation(Long id);
}
