package cml.rest.file.storage.test.dto;

import cml.rest.file.storage.test.annotation.FileTagsConstraint;
import java.util.List;

public class TagsRequestDto {

    @FileTagsConstraint
    List<String> tags;

    public TagsRequestDto() {
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
