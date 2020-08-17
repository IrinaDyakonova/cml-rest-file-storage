package cml.rest.file.storage.test.rest;

import cml.rest.file.storage.test.dto.FileCreatedDto;
import cml.rest.file.storage.test.dto.FileListResponseDto;
import cml.rest.file.storage.test.dto.FileRequestDto;
import cml.rest.file.storage.test.dto.ResponseDto;
import cml.rest.file.storage.test.mapper.FileMapper;
import cml.rest.file.storage.test.model.File;
import cml.rest.file.storage.test.service.FileService;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/file")
public class RestEndPoints {

    private final FileService fileService;
    private final FileMapper fileMapper;

    public RestEndPoints(FileService fileService, FileMapper fileMapper) {
        this.fileService = fileService;
        this.fileMapper = fileMapper;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<FileListResponseDto> allFiles(
            @RequestParam(value = "tags", required = false) List<String> tags,
            @RequestParam(value = "page", required = false) @Size(min = 1) Integer page,
            @RequestParam(value = "size", required = false) @Size(min = 1) Integer size,
            @RequestParam(value = "q", required = false) String query) {

        return new ResponseEntity<>(
                fileService.allFiles(tags,query,page,size),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<FileCreatedDto> postFile(
            @Valid @RequestBody FileRequestDto fileRequestDto) {
        File file = fileService.saveFile(fileMapper.toEntity(fileRequestDto));
        return new ResponseEntity<>(fileMapper.formatResponse(file), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}/tags")
    public ResponseEntity<ResponseDto> postFileTag(
            @PathVariable("id") String id,
            @Valid @RequestBody List<String> tags) {
        ResponseDto responseDto = new ResponseDto();

        if (fileService.putTags(id, tags)) {
            responseDto.setSuccess(true);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            responseDto.setSuccess(false);
            if (id.length() != 20) {
                responseDto.setError("Size id should be equal 20");

            } else {
                responseDto.setError("No Record With This Id or filed to add tags for file");

            }
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @ResponseBody
    public ResponseEntity<ResponseDto> deleteFile(@PathVariable("id") String id) {
        ResponseDto responseDto = new ResponseDto();

        if (fileService.deleteFile(id)) {
            responseDto.setSuccess(true);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            responseDto.setSuccess(false);
            if (id.length() != 20) {
                responseDto.setError("Size should be equal 20");
            } else {
                responseDto.setError("No Record With This Id!");
            }
            return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);

        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}/tags")
    @ResponseBody
    public ResponseEntity<ResponseDto> deleteTags(
            @PathVariable("id") String id,
            @Valid @RequestBody List<String> tags) {
        ResponseDto responseDto = new ResponseDto();

        if (fileService.deleteTags(id, tags)) {
            responseDto.setSuccess(true);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);

        } else {
            responseDto.setSuccess(false);
            if (id.length() != 20) {
                responseDto.setError("Size id should be equal 20");

            } else {
                responseDto.setError("No Record With This Id or filed to delete tags for file");

            }
            return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);

        }
    }
}
