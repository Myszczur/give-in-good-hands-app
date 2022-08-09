package pl.coderslab.charity.validation;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword arg0) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 20),
                new UppercaseCharacterRule(1),
                new DigitCharacterRule(1),
                new SpecialCharacterRule(1),
                new LowercaseCharacterRule(1)));

        RuleResult result = validator.validate(new PasswordData(password));
        return result.isValid();
    }
}