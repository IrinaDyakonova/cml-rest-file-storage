package cml.rest.file.storage.test.repository;

import cml.rest.file.storage.test.model.File;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends ElasticsearchRepository<File, String> {

}
