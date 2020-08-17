package cml.rest.file.storage.test.dto;

import cml.rest.file.storage.test.annotation.FileNameConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class FileRequestDto {

    @FileNameConstraint(message = "")
    private String name;
    @Min(value = 0, message = "Size should not be less than 0")
    @NotNull(message = "Size param is required")
    private Long size;

    public FileRequestDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
