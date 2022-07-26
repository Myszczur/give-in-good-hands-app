package pl.coderslab.charity.model;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.charity.validation.EmailUnique;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotEmpty(message = "Podaj imię")
    private String firstName;
    @NotNull
    @NotEmpty(message = "Podaj nazwisko")
    private String lastName;
    private boolean enabled;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    @EmailUnique
    @Column(unique = true, length = 60)
    @NotBlank(message = "Musisz podać Email")
    private String email;
    @NotBlank(message = "Musisz podać hasło")
//    @ValidPassword(message = "Hasło musi mieć min 8 znaków, 1 wielka litera, 1 małą litera, 1 znak specjalny i 1 liczbę.")
    private String password;
    private boolean accountNonLocked;
    private boolean accountNonExpired;
}