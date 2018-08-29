package telerikacademy.extensionrepository.services.base;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import telerikacademy.extensionrepository.models.File;

import java.nio.file.Path;

public interface FileStorageService {
    File getById(long id);

    void store(MultipartFile file, long id);

    Path load(long id, String filename);

    Resource loadAsResource(long id, String filename);

}
