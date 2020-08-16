package cml.rest.file.storage.test.validator;

import cml.rest.file.storage.test.annotation.FileTagsConstraint;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FileTagsValidator implements ConstraintValidator<FileTagsConstraint, List<String>> {

    @Override
    public boolean isValid(List<String> tags, ConstraintValidatorContext context) {

        Set<String> tagsSet = new HashSet<>(tags);

        return tags != null
                && tags.size() != 0
                && tags.size()
                == tagsSet.size();
    }
}
