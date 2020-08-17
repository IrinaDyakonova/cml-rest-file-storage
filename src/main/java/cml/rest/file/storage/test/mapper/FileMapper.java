package cml.rest.file.storage.test.mapper;

import cml.rest.file.storage.test.dto.FileCreatedDto;
import cml.rest.file.storage.test.dto.FileRequestDto;
import cml.rest.file.storage.test.model.File;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Component;

@Component
public class FileMapper {

    public Object toDto(SearchHit file) {
        return file.getContent();
    }

    public FileCreatedDto formatResponse(File file) {
        FileCreatedDto fileCreatedDto = new FileCreatedDto();
        fileCreatedDto.setId(file.getId());
        return fileCreatedDto;
    }

    public File toEntity(FileRequestDto fileRequestDto) {
        File file = new File();
        file.setName(fileRequestDto.getName());
        file.setSize(fileRequestDto.getSize());
        return file;
    }

}
