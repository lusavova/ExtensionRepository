package telerikacademy.extensionrepository.services.base;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface StorageService {
    void store(MultipartFile file, long id);

    Path load(long id, String filename);

    Resource loadAsResource(long id, String filename);

}
