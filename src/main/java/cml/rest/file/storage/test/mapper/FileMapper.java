package cml.rest.file.storage.test.mapper;

import cml.rest.file.storage.test.dto.FileRequestDto;
import cml.rest.file.storage.test.dto.FileResponseDto;
import cml.rest.file.storage.test.model.File;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Component;

@Component
public class FileMapper {

    public FileResponseDto toDto(File file) {
        FileResponseDto fileResponseDto = new FileResponseDto();
        fileResponseDto.setId(file.getId());
        fileResponseDto.setName(file.getName());
        fileResponseDto.setSize(file.getSize());
        fileResponseDto.setTags(file.getTags());
        return fileResponseDto;
    }

    public Object toDto(SearchHit file) {
        FileResponseDto fileResponseDto = new FileResponseDto();
        Object content = file.getContent();
        return content;
    }

    public File toEntity(FileRequestDto fileRequestDto) {
        File file = new File();
        file.setName(fileRequestDto.getName());
        file.setSize(fileRequestDto.getSize());
        return file;
    }

}
