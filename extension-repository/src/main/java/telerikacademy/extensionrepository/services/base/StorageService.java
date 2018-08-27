package telerikacademy.extensionrepository.services.base;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface StorageService {

    void init();

    void store(MultipartFile file, long id);

    Path load(String filename);

    Resource loadAsResource(String filename);

}
