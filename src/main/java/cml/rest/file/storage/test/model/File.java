package cml.rest.file.storage.test.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "f1index", type = "files")
public class File {

    @Id
    private String id;
    private String name;
    private long size;
    private List<String> tags = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        for (String tag : tags) {
            this.tags.add(tag);
        }
        Set<String> tagsSet = new HashSet<>(this.tags);
        this.tags.clear();
        for (String tag : tagsSet) {
            this.tags.add(tag);
        }
    }
}
