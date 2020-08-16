package cml.rest.file.storage.test.dto;

import java.util.ArrayList;
import java.util.List;

public class FileListResponseDto {

    private Long total;
    private List<Object> page = new ArrayList<>();

    public FileListResponseDto() {
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<Object> getPage() {
        return page;
    }

    public void setPage(List<Object> page) {
        this.page = page;
    }
}
