package walter.duncan.dndwebapi.services.filemanagement;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import walter.duncan.dndwebapi.exceptions.EmptyFileException;
import walter.duncan.dndwebapi.exceptions.ResourceNotFoundException;

@Service
public class FileSystemStorageService implements StorageService {
    private final Path fileSystemRoot;

    public FileSystemStorageService(@Value("${file-system-root-location}") String fileSystemRootLocation) {
        this.fileSystemRoot = Paths.get(fileSystemRootLocation).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileSystemRoot);
        } catch (IOException e) {
            // TODO: This should never happen under normal circumstances, but if this does happen log it.
        }
    }

    @Override
    public String storeFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new EmptyFileException();
        }

        var fileName = UUID.randomUUID().toString();
        fileName += file.getContentType() != null
                ? "." + file.getContentType().split("/")[1]
                : ".bin"; // Fallback to binary
        var filePath = Paths.get(this.fileSystemRoot + FileSystems.getDefault().getSeparator() + fileName);

        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Error trying to upload file :(");
        }

        return fileName;
    }

    @Override
    public Resource loadFile(String fileName) {
        try {
            Path path = this.fileSystemRoot.resolve(fileName).normalize();
            Resource resource = new UrlResource(path.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                throw new ResourceNotFoundException("The file doesn't exist or is not readable: " + fileName);
            }

            return resource;
        } catch (MalformedURLException e) {
            throw new ResourceNotFoundException("Invalid file path: " + fileName);
        }
    }

    @Override
    public void removeFile(String fileName) {
        try {
            Path targetPath = this.fileSystemRoot.resolve(fileName).normalize();

            if (!Files.exists(targetPath)) {
                throw new ResourceNotFoundException("File does not exist: " + fileName);
            }

            Files.delete(targetPath);
        } catch (IOException e) {
            throw new RuntimeException("Error while deleting file: " + fileName, e);
        }
    }
}