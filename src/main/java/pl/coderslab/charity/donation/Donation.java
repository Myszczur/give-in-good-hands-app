package pl.coderslab.charity.donation;

import lombok.Data;
import lombok.ToString;
import pl.coderslab.charity.Institution.Institution;
import pl.coderslab.charity.category.Category;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Data
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    @ManyToMany
    private List<Category> categories = new ArrayList<>();
    @ManyToOne
    private Institution institution;
    private String street;
    private String city;
    private String zipCode;
    private LocalDate pickUpDate;
    private LocalTime pickUpTime;
    private String pickUpComment;

}
