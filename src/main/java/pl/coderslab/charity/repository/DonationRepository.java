package pl.coderslab.charity.repository;

import com.sun.xml.bind.v2.TODO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.model.Donation;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    //TODO: jpql
    @Query(value = "select sum(quantity) from donation", nativeQuery = true)
    Integer sumDonatedBags();

    //TODO: jpql
    @Query(value = "select count(categories_id) from donation_categories", nativeQuery = true)
    Integer countDonatedCategory();
}
