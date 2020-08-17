package cml.rest.file.storage.test.validator;

import cml.rest.file.storage.test.annotation.FileNameConstraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FileNameValidator implements ConstraintValidator<FileNameConstraint, String> {
    private String message;

    @Override
    public void initialize(final FileNameConstraint constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        boolean valid = true;
        String regex = ".+\\.[A-Za-z0-9]{2,4}$";

        if (name == null) {
            valid = false;
            message = "Name param is required";
        } else if (name.length() == 0) {
            valid = false;
            message = "Name param is cannot be empty";
        } else if (!(name.matches(regex)
                && name.length() >= 6
                && name.length() <= 28)) {
            valid = false;
            message = "File name format is invalid!";
        }
        if (!valid) {
            context
                    .buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }
        return valid;
    }
}
