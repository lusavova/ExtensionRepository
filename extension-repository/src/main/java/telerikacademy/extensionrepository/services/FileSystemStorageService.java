package telerikacademy.extensionrepository.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import telerikacademy.extensionrepository.config.StorageProperties;
import telerikacademy.extensionrepository.data.FileRepository;
import telerikacademy.extensionrepository.exceptions.StorageException;
import telerikacademy.extensionrepository.exceptions.StorageFileNotFoundException;
import telerikacademy.extensionrepository.models.File;
import telerikacademy.extensionrepository.models.User;
import telerikacademy.extensionrepository.services.base.StorageService;
import telerikacademy.extensionrepository.services.base.UserService;
import telerikacademy.extensionrepository.validator.ZipValidator;

@Service
public class FileSystemStorageService implements StorageService {
    private Path rootLocation;
    private FileRepository fileRepository;
    private UserService userService;

    @Autowired
    public FileSystemStorageService(StorageProperties properties,
                                    FileRepository fileRepository,
                                    UserService userService) {
        this.rootLocation = Paths.get(properties.getLocation());
        this.fileRepository = fileRepository;
        this.userService = userService;
    }

    @Override
    public void store(MultipartFile multipartFile, long id) {
        User user = checkUser(id);

        String filename = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try {
            if (multipartFile.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }

            String username = user.getUsername();

            try (InputStream inputStream = multipartFile.getInputStream()) {
                Path path = Paths.get(rootLocation + "\\" + username);

                if (!Files.exists(path)) {
                    boolean createDirectory = new java.io.File(rootLocation + "/" + username).mkdirs();

                    if (!createDirectory) {
                        throw new IllegalArgumentException("Cannot create directory with name = " + username);
                    }
                }

                long size = multipartFile.getSize();
                int lastIndexOfDot = filename.lastIndexOf('.');
                String type = filename.substring(lastIndexOfDot + 1);
                String location = path.toString();

                String downloadLink = location + "\\" + filename;

                File file = new File(filename, type, size, location, downloadLink);
                Files.copy(inputStream,
                        path.resolve(file.getFileName()),
                        StandardCopyOption.REPLACE_EXISTING);

                fileRepository.save(file);


                System.out.println("DEBUG");
            }

        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }

    private User checkUser(long id) {
        User user = userService.getUserById(id);

        if (user == null) {
            throw new IllegalArgumentException("Cannot find user with id = " + id);
        }

        return user;
    }

    @Override
    public Path load(long id, String filename) {
        return Paths.get(Objects.requireNonNull(fileRepository.findAll().stream()
                .filter(x -> x.getOwner().getId() == id
                        && x.getFileName().equals(filename))
                .findFirst().orElse(null)).getDownloadLink());
    }

//    @Override
//    public Path load(String filename) {
//        return rootLocation.resolve(filename);
//    }

    @Override
    public Resource loadAsResource(long id, String filename) {
        try {
            Path file = load(id, filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }
}
