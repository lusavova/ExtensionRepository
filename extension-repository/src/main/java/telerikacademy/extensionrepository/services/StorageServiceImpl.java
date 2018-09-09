package telerikacademy.extensionrepository.services;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import telerikacademy.extensionrepository.config.StorageProperties;
import telerikacademy.extensionrepository.data.FileRepository;
import telerikacademy.extensionrepository.exceptions.StorageException;
import telerikacademy.extensionrepository.exceptions.StorageFileNotFoundException;
import telerikacademy.extensionrepository.models.File;
import telerikacademy.extensionrepository.services.base.StorageService;
import telerikacademy.extensionrepository.data.ProductsRepository;
import telerikacademy.extensionrepository.exceptions.ProductNotFoundExeption;
import telerikacademy.extensionrepository.models.Product;
import telerikacademy.extensionrepository.models.User;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import telerikacademy.extensionrepository.constants.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.*;
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

    @Autowired
    public StorageServiceImpl(StorageProperties storageProperties,
                              FileRepository fileRepository,
                              ProductsRepository productsRepository) {
        this.rootLocation = storageProperties.getLocation();
        this.fileRepository = fileRepository;
        this.productsRepository = productsRepository;
    }

    @Override
    public File findById(long id) {
        System.out.println(fileRepository.existsById(id));

        System.out.println();
        return fileRepository.findById(id)
                .orElseThrow(() -> new StorageFileNotFoundException("Cannot find file with id = " + id));
    }

    @Override
    public void createDirectory(Path path) {
        String pathname = path.toString();
        if (!Files.exists(path)) {
            boolean createDirectory = new java.io.File(pathname).mkdirs();

            if (!createDirectory) {
                throw new IllegalArgumentException("Cannot create directory.");
            }
        }
    }

    @Override
    public File store(MultipartFile multipartFile, User user, String type) {
        File file;
        if (multipartFile.isEmpty()) {
            throw new StorageException("Failed to store empty file.");
        }

        String filename = Objects.requireNonNull(multipartFile.getOriginalFilename());
        if (filename.contains("..")) {
            throw new StorageException(
                    "Cannot store file with relative path outside current directory.");
        }

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

    @Override
    public void deleteAllUserFilesFromSystem(User user){
        String username = user.getUsername();
        Path path = Paths.get(Constants.FILE_LOCATION + '/' + username);
        deleteFilesFromSystem(path);
    }

    @Override
    public void deleteFilesFromSystem(Path path){
        try {
            java.io.File file = new java.io.File(path.toString());
            FileUtils.deleteDirectory(file);
        } catch (NoSuchFileException ex) {
            System.err.format("%s: no such" + " file or directory%n", path);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
