package pl.coderslab.charity.valid;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class EmailValidator implements ConstraintValidator<EmailUnique, String> {

    private final UserRepository userRepository;

    @Override
    public void initialize(EmailUnique emailUnique) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return userRepository.findByEmail(email) == null;
    }
}