package cml.rest.file.storage.test.util;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class ExtensionTags {
    Map<String, String> tags = new HashMap<>();

    public ExtensionTags() {
        tags.put("doc","document");
        tags.put("docx","document");
        tags.put("pdf","document");
        tags.put("txt","document");
        tags.put("xls","table");
        tags.put("xlsx","table");
        tags.put("zip","archive");
        tags.put("rar","archive");
        tags.put("jpg","photo");
        tags.put("png","photo");
        tags.put("mp3","music");
        tags.put("wav","music");
        tags.put("mp4","video");
        tags.put("avi","video");
    }

    public String getTagByExtension(String extension) {
        return tags.get(extension);
    }
}
