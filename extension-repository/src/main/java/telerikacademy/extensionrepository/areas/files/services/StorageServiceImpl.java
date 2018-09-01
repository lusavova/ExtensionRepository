package telerikacademy.extensionrepository.areas.files.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import telerikacademy.extensionrepository.areas.files.config.StorageProperties;
import telerikacademy.extensionrepository.areas.files.data.FileRepository;
import telerikacademy.extensionrepository.areas.files.exeptions.StorageException;
import telerikacademy.extensionrepository.areas.files.exeptions.StorageFileNotFoundException;
import telerikacademy.extensionrepository.areas.files.models.File;
import telerikacademy.extensionrepository.areas.files.services.base.StorageService;
import telerikacademy.extensionrepository.areas.products.data.ProductsRepository;
import telerikacademy.extensionrepository.areas.products.exeptions.ProductNotFoundExeption;
import telerikacademy.extensionrepository.areas.products.models.Product;
import telerikacademy.extensionrepository.areas.users.models.User;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import telerikacademy.extensionrepository.areas.users.services.base.UserService;
import telerikacademy.extensionrepository.constants.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StorageServiceImpl implements StorageService {
    private final String rootLocation;
    private FileRepository fileRepository;
    private ProductsRepository productsRepository;
    private UserService userService;

    @Autowired
    public StorageServiceImpl(StorageProperties storageProperties,
                              FileRepository fileRepository,
                              ProductsRepository productsRepository,
                              UserService userService) {
        this.rootLocation = storageProperties.getLocation();
        this.fileRepository = fileRepository;
        this.productsRepository = productsRepository;
        this.userService = userService;
    }

    @Override
    public File getById(long id) {
        return fileRepository.findById(id)
                .orElseThrow(() -> new StorageFileNotFoundException("Cannot find file with id = " + id));
    }

    @Override
    public void createDirectory(Path path) {
        String pathname = path.toString();
        if (!Files.exists(path)) {
            boolean createDirectory = new java.io.File(pathname).mkdirs();

            if (!createDirectory) {
                throw new IllegalArgumentException("Cannot create directory");
            }
        }
    }

    @Override
    public File store(MultipartFile multipartFile, long userId, String type) {
        File file;
        if (multipartFile.isEmpty()) {
            throw new StorageException("Failed to store empty file.");
        }

        String filename = Objects.requireNonNull(multipartFile.getOriginalFilename());
        if (filename.contains("..")) {
            throw new StorageException(
                    "Cannot store file with relative path outside current directory.");
        }

        User user = userService.findById(userId);

        Path path = Paths.get(rootLocation + "\\" + user.getUsername());

        try (InputStream inputStream = multipartFile.getInputStream()) {
            createDirectory(path);
            file = createFile(multipartFile, path, user, type);
            Files.copy(inputStream,
                    path.resolve(file.getFileName()),
                    StandardCopyOption.REPLACE_EXISTING);
            fileRepository.save(file);
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }

        return file;
    }

    @Override
    public Path load(long productId) {
        Product product = productsRepository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundExeption("Cannot find product with id = " + productId));
        File file = product.getFile();
        return Paths.get(file.getDownloadLink());
    }

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

    private File createFile(MultipartFile multipartFile, Path path, User user, String type) {
        String filename = multipartFile.getOriginalFilename();
        String name = filename.substring(0, filename.lastIndexOf('.'));

        Set<File> files = fileRepository.findAll()
                .stream()
                .filter(f -> f.getFileName().equals(name))
                .collect(Collectors.toSet());
        if (files.size() != 0) {
            SimpleDateFormat localDateFormat = new SimpleDateFormat(Constants.FILE_DATE_FORMAT);
            String time = localDateFormat.format(new Date());
            filename = time + filename;
        }

        long size = multipartFile.getSize();
        String location = path.toString();
        String downloadLink = location + "\\" + filename;
        File file = new File(filename, type, size, location, downloadLink);
        file.setOwner(user);
        return file;
    }
}
