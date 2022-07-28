package pl.coderslab.charity.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailUnique, String> {

    @Override
    public void initialize(EmailUnique constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext cxt) {

        if (email == null) {
            return true;
        }
        return email.isEmpty();
    }
}