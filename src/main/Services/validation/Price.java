package main.Services.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;


@Target(FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PriceValidator.class)
public @interface Price {
    String message() default "The price is not correctly specified";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
