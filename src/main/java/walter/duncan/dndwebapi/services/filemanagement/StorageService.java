package walter.duncan.dndwebapi.services.filemanagement;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    void init();
    String storeFile(MultipartFile file);
    Resource loadFile(String fileName);
    void removeFile(String fileName);
    void removeAll();
}