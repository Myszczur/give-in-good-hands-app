package pl.coderslab.charity.valid;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
public @interface EmailUnique {

    String message() default "Email is Duplication";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}