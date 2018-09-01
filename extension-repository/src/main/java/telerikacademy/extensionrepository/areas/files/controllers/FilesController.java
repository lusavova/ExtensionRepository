package telerikacademy.extensionrepository.areas.files.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import telerikacademy.extensionrepository.areas.files.enums.StorageType;
import telerikacademy.extensionrepository.areas.files.exeptions.StorageFileNotFoundException;
import telerikacademy.extensionrepository.areas.files.models.File;
import telerikacademy.extensionrepository.areas.files.services.base.StorageService;
import telerikacademy.extensionrepository.areas.files.validator.ImageValidator;
import telerikacademy.extensionrepository.areas.files.validator.ZipValidator;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FilesController {
    private StorageService storageService;

    @Autowired
    public FilesController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/upload/file/{userId}")
    @ResponseBody
    public File uploadFile(@RequestBody MultipartFile file, @PathVariable long userId) {
        ZipValidator.checkFile(file);
        String type = StorageType.FILE.name();
        File f = storageService.store(file, userId, type);
        return f;
    }

    @PostMapping("/upload/image/{userId}")
    @ResponseBody
    public File uploadImage(@RequestBody MultipartFile image, @PathVariable long userId) {
        ImageValidator.checkImage(image);
        String type = StorageType.IMAGE.name();
        File file = storageService.store(image, userId, type);
        return file;
    }

    @PostMapping("/upload/images/{userId}")
    @ResponseBody
    public List<File> uploadImages(@PathVariable long userId, @RequestBody MultipartFile... images) {
        List<File> imgs = new ArrayList<>();
        for (MultipartFile image : images) {
            ImageValidator.checkImage(image);
            String type = StorageType.IMAGE.name();
            File file = storageService.store(image, userId, type);
            imgs.add(file);
        }
        return imgs;
    }

    @GetMapping("/files/download/product/{id}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable("id") long id) {
        Resource file = storageService.loadAsResource(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}