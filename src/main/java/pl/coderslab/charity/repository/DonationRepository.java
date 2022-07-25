package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.model.Donation;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query(value = "select sum(quantity) from donation", nativeQuery = true)
    Integer sumDonatedBags();

    @Query(value = "select count(categories_id) from donation_categories", nativeQuery = true)
    Integer countDonatedCategory();
}
