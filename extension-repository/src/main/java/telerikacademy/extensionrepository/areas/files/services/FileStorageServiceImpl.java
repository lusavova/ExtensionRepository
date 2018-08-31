package telerikacademy.extensionrepository.areas.files.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import telerikacademy.extensionrepository.areas.files.config.StorageProperties;
import telerikacademy.extensionrepository.areas.files.data.FileRepository;
import telerikacademy.extensionrepository.areas.products.data.ProductsRepository;
import telerikacademy.extensionrepository.areas.files.exeptions.NoSuchEntityExeption;
import telerikacademy.extensionrepository.areas.files.exeptions.NoSuchUserExeption;
import telerikacademy.extensionrepository.areas.files.exeptions.StorageException;
import telerikacademy.extensionrepository.areas.files.exeptions.StorageFileNotFoundException;
import telerikacademy.extensionrepository.areas.files.models.File;
import telerikacademy.extensionrepository.areas.products.models.Product;
import telerikacademy.extensionrepository.areas.users.models.User;
import telerikacademy.extensionrepository.areas.files.services.base.FileStorageService;
import telerikacademy.extensionrepository.areas.users.services.base.UserService;
import telerikacademy.extensionrepository.areas.files.validator.ImageValidator;
import telerikacademy.extensionrepository.areas.files.validator.ZipValidator;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    private static final String IMAGE_FOLDER_NAME = "images";
    private static final String EXTENSIONS_FOLDER_NAME = "extensions";

    private Path rootLocation;
    private FileRepository fileRepository;
    private UserService userService;
    private ProductsRepository productsRepository;

    @Autowired
    public FileStorageServiceImpl(StorageProperties properties,
                                  FileRepository fileRepository,
                                  UserService userService,
                                  ProductsRepository productsRepository) {
        this.rootLocation = Paths.get(properties.getLocation());
        this.fileRepository = fileRepository;
        this.userService = userService;
        this.productsRepository = productsRepository;
    }

    @Override
    public File getById(long id) {
        return fileRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityExeption("Cannot find file with id = " + id));
    }

    @Override
    public void storeImage(MultipartFile image, long id) {
        ImageValidator.checkImage(image);
        User user = checkUser(id);
        store(image, user, IMAGE_FOLDER_NAME);
    }

    @Override
    public void storeFile(MultipartFile file, long id) {
        ZipValidator.checkFile(file);
        User user = checkUser(id);
        store(file, user, EXTENSIONS_FOLDER_NAME);
    }

//    @Override
//    public Path load(long userId, String filename) {
//        List<File> userFiles = fileRepository.findAll()
//                .stream()
//                .filter(f -> f.getOwner().getId() == userId)
//                .collect(Collectors.toList());
//
//        File file = userFiles
//                .stream()
//                .filter(x -> x.getFileName().equals(filename))
//                .findFirst()
//                .orElseThrow(() -> new NoSuchEntityExeption("Cannot find file with name: " + filename));
//
//        return Paths.get(file.getDownloadLink());
//    }

    @Override
    public Resource loadAsResource(long id) {
        try {
            Path file = load(id);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException(
                        "Could not read file!");
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file!", e);
        }
    }

    private void store(MultipartFile multipartFile, User user, String folderName) {
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
                Path path = Paths.get(rootLocation + "\\" + username + "\\" + folderName);

                if (!Files.exists(path)) {
                    boolean createDirectory = new java.io.File(rootLocation + "/" + username + "/" + folderName).mkdirs();

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
                file.setOwner(user);
                Files.copy(inputStream,
                        path.resolve(file.getFileName()),
                        StandardCopyOption.REPLACE_EXISTING);

                fileRepository.save(file);
            }

        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }

    private Path load(long productId) {
        Product product = productsRepository
                .findById(productId)
                .orElseThrow(()->new NoSuchEntityExeption("Cannot find product with id = " + productId));
        File file = product.getFile();
        return Paths.get(file.getDownloadLink());
    }

    private User checkUser(long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new NoSuchUserExeption("Cannot find user with id = " + id);
        }
        return user;
    }
}
