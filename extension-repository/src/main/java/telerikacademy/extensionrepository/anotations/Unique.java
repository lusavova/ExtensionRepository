package telerikacademy.extensionrepository.anotations;

import telerikacademy.extensionrepository.common.FieldValueExists;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueValidator.class)
@Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE})
public @interface Unique {
    String message() default "{unique.value.violation}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<? extends FieldValueExists> service();
    String serviceQualifier() default "";
    String fieldName();
}