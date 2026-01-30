package walter.duncan.dndwebapi.services.filemanagement;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    String storeFile(MultipartFile file);
    Resource loadFile(String fileName);
}