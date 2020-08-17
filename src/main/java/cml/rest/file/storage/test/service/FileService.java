package cml.rest.file.storage.test.service;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.regexpQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

import cml.rest.file.storage.test.dto.FileListResponseDto;
import cml.rest.file.storage.test.mapper.FileMapper;
import cml.rest.file.storage.test.model.File;
import cml.rest.file.storage.test.repository.FileRepository;
import cml.rest.file.storage.test.util.ExtensionTags;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    private FileRepository fileRepository;
    private ElasticsearchOperations operations;
    private final FileMapper fileMapper;
    private final ExtensionTags extensionTags;

    public FileService(
            FileRepository fileRepository,
            ElasticsearchOperations operations,
            FileMapper fileMapper,
            ExtensionTags extensionTags) {
        this.fileRepository = fileRepository;
        this.operations = operations;
        this.fileMapper = fileMapper;
        this.extensionTags = extensionTags;
    }

    public FileListResponseDto allFiles(
            List<String> tags,
            String query,
            Integer page,
            Integer size) {
        List<Object> allFiles = new ArrayList<>();

        BoolQueryBuilder builder = boolQuery();
        if (tags != null && tags.size() != 0) {
            for (String tag: tags) {
                builder.must(termQuery("tags", tag));
            }
        }

        if (query != null && query.length() >= 3) {
            builder.must(regexpQuery("name", ".*" + query + ".*"));
        }

        page = page == null || page == 0 ? 0 : page - 1;
        size = size == null ? 10 : size;

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(builder)
                .withPageable(PageRequest.of(page, size))
                .build();
        SearchHits<File> files = operations.search(searchQuery, File.class);
        FileListResponseDto fileListResponseDto = new FileListResponseDto();
        fileListResponseDto.setTotal(files.getTotalHits());
        for (SearchHit searchHit: files) {
            allFiles.add(fileMapper.toDto(searchHit));
        }
        fileListResponseDto.setPage(allFiles);

        return fileListResponseDto;
    }

    public File saveFile(File file) {
        String[] fileExtension = file.getName().split("\\.");

        String tag = extensionTags.getTagByExtension(fileExtension[fileExtension.length - 1]);
        if (tag != null) {
            file.getTags().add(tag);
        }
        File fileFromDB = fileRepository.save(file);
        return fileFromDB;
    }

    public boolean putTags(String key, List<String> tags) {
        Set<String> tagsSet = new HashSet<>(tags);

        if (tags != null
                && tags.size() != 0
                && tags.size()
                == tagsSet.size()) {

            if (fileRepository.findById(key).isPresent()) {
                fileRepository.findById(key).ifPresent(file
                                -> {
                            file.setTags(tags);
                            fileRepository.save(file);
                        }
                );

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean deleteTags(String key, List<String> tags) {
        Set<String> tagsSet = new HashSet<>(tags);
        if (tags != null
                && tags.size() != 0
                && tags.size()
                == tagsSet.size()) {
            if (fileRepository.findById(key).isPresent()) {
                File file = fileRepository.findById(key).get();
                for (String tag: tags) {
                    int indexOf = file.getTags().indexOf(tag);
                    if (indexOf == -1) {
                        return false;
                    }
                }

                for (String tag: tags
                ) {
                    file.getTags().remove(tag);

                }
                fileRepository.save(file);
                return true;

            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean deleteFile(String key) {
        if (fileRepository.findById(key).isPresent()) {
            fileRepository.findById(key).ifPresent(file -> fileRepository.delete(file));
            return true;
        } else {
            return false;
        }
    }

}
