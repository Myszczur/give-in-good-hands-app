package pl.coderslab.charity.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
public @interface EmailUnique {

    String message() default "Podany email już istnieje!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}