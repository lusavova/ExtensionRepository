package telerikacademy.extensionrepository.services.base;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import telerikacademy.extensionrepository.models.File;
import telerikacademy.extensionrepository.models.User;

import java.nio.file.Path;

public interface StorageService {
    File findById(long id);

    void createDirectory(Path path);

    File store(MultipartFile multipartFile, User user, String type);

    Path load(long id);

    Resource loadAsResource(long id);

    void deleteFilesFromSystem(Path path);

    void deleteAllUserFilesFromSystem(User user);
}
