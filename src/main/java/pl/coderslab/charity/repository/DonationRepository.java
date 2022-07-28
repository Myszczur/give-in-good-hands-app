package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.model.Donation;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("SELECT SUM(d.quantity) FROM Donation d")
    Integer sumDonatedBags();


    @Query("SELECT COUNT(d.id) FROM Donation d")
    Integer countDonatedCategory();
}
