package telerikacademy.extensionrepository.areas.files.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import telerikacademy.extensionrepository.areas.files.enums.StorageType;
import telerikacademy.extensionrepository.areas.files.exeptions.StorageException;
import telerikacademy.extensionrepository.areas.files.exeptions.StorageFileNotFoundException;
import telerikacademy.extensionrepository.areas.files.models.File;
import telerikacademy.extensionrepository.areas.files.services.base.StorageService;
import telerikacademy.extensionrepository.areas.files.validator.ImageValidator;
import telerikacademy.extensionrepository.areas.files.validator.ZipValidator;
import telerikacademy.extensionrepository.areas.products.exeptions.ProductNotFoundExeption;
import telerikacademy.extensionrepository.areas.users.models.User;
import telerikacademy.extensionrepository.areas.users.services.base.UserService;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/files")
public class FilesController {
    private StorageService storageService;
    private UserService userService;

    @Autowired
    public FilesController(StorageService storageService,
                           UserService userService) {
        this.storageService = storageService;
        this.userService = userService;
    }

    @PostMapping("/upload/file/{userId}")
    @ResponseBody
    public File uploadFile(@RequestBody MultipartFile file, @PathVariable long userId) {
        if (file == null) {
            throw new IllegalArgumentException("File cannot be null.");
        }
        String type = StorageType.FILE.name();
        new ZipValidator().checkFile(file);
        User user = userService.findById(userId);
        return storageService.store(file, user, type);
    }

    @PostMapping("/upload/image/{userId}")
    @ResponseBody
    public File uploadImage(@RequestBody MultipartFile file, @PathVariable long userId) {
        String type = StorageType.IMAGE.name();
        if (file == null) {
            throw new IllegalArgumentException("Image cannot be null.");
        }
        new ImageValidator().checkImage(file);
        User user = userService.findById(userId);
        File f = storageService.store(file, user, type);
        return f;
    }

    @PostMapping("/upload/images/{userId}")
    @ResponseBody
    public List<File> uploadImages(@PathVariable long userId, @RequestBody MultipartFile... files) {
        List<File> imgs = new ArrayList<>();
        for (MultipartFile image : files) {
            if (image == null) {
                throw new IllegalArgumentException("Image file cannot be null.");
            }
            String type = StorageType.IMAGE.name();
            new ImageValidator().checkImage(image);
            User user = userService.findById(userId);
            File file = storageService.store(image, user, type);
            imgs.add(file);
        }
        return imgs;
    }

    @GetMapping("/file/get/{id}")
    @ResponseBody
    public File findById(@PathVariable long id) {
        return storageService.findById(id);
    }

    @GetMapping("/files/download/product/{id}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable("id") long id) {
        Resource file = storageService.loadAsResource(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @ExceptionHandler
    public String handleProductNotFoundExeption(ProductNotFoundExeption ex){
        return ex.getMessage();
    }

    @ExceptionHandler
    public String handleStorageException(StorageException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler
    public String handleStorageFileNotFound(StorageFileNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler
    public String catchIllegalArgumentExceptions(IllegalArgumentException ex) {
        return ex.getMessage();
    }
}