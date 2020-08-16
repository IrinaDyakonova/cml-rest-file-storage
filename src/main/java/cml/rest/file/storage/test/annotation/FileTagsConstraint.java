package cml.rest.file.storage.test.annotation;

import cml.rest.file.storage.test.validator.FileTagsValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = FileTagsValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FileTagsConstraint {
    String message() default "Tags format is invalid!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
