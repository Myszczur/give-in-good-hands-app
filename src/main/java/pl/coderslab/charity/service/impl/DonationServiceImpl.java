package pl.coderslab.charity.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationServiceImpl implements DonationService {
    private final DonationRepository donationRepository;
    private final UserService userService;

    @Override
    public void addDonations(Donation donation, User user) {
        donation.setStatus("Nieodebrane");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        donation.setCreated(LocalDateTime.now().format(formatter));
        donation.setUser(user);
        donationRepository.save(donation);
    }

    @Override
    public List<Donation> findAllDonationByUser(UserDetails userDetails) {
        User currentUser = userService.findByEmail(userDetails.getUsername());
        List<Donation> donationList = donationRepository.findAllByUserIdOrderByStatusDescReceivedDescCreatedDesc(currentUser.getId());
        return donationList;
    }

    @Override
    public void updateDonation(Long id) {
        Donation receivedDonation = donationRepository.getOne(id);
        receivedDonation.setStatus("Odebrane");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        receivedDonation.setReceived(LocalDateTime.now().format(formatter));
        donationRepository.save(receivedDonation);
    }
}
