package cml.rest.file.storage.test.validator;

import cml.rest.file.storage.test.annotation.FileNameConstraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FileNameValidator implements ConstraintValidator<FileNameConstraint, String> {

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        String regex = ".+\\.[A-Za-z0-9]{2,4}$";
        return name != null
                && name.matches(regex)
                && name.length() >= 6
                && name.length() <= 28;
    }
}
