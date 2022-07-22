package pl.coderslab.charity.donation;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.charity.Institution.Institution;
import pl.coderslab.charity.category.Category;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
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
    private String phoneNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;
    private LocalTime pickUpTime;
    private String pickUpComment;

}
