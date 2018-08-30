package telerikacademy.extensionrepository.services.base;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import telerikacademy.extensionrepository.models.File;

import java.nio.file.Path;

public interface FileStorageService {
    File getById(long id);

    void storeImage(MultipartFile image, long id);

    void storeFile(MultipartFile file, long id);

    Resource loadAsResource(long id);
}
