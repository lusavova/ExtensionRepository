package telerikacademy.extensionrepository.areas.files.services.base;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import telerikacademy.extensionrepository.areas.files.models.File;

public interface FileStorageService {
    File getById(long id);

    void storeImage(MultipartFile image, long id);

    void storeFile(MultipartFile file, long id);

    Resource loadAsResource(long id);
}
