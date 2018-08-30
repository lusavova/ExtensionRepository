package telerikacademy.extensionrepository.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import telerikacademy.extensionrepository.exceptions.StorageFileNotFoundException;
import telerikacademy.extensionrepository.services.base.FileStorageService;

@Controller
public class FilesController {
    private final FileStorageService fileStorageService;

    @Autowired
    public FilesController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/upload/file/{id}")
    public @ResponseBody
    void handleFileUpload(@RequestBody MultipartFile file, @PathVariable long id) {
        System.out.println("FILENAME: " + file.getOriginalFilename());
        fileStorageService.storeFile(file, id);
    }

    //userId
    @PostMapping("/upload/image/{id}")
    public @ResponseBody
    void handleImageUpload(@RequestBody MultipartFile image, @PathVariable long id){
        System.out.println("image" + image.getOriginalFilename());
        fileStorageService.storeImage(image, id);
    }

    @PostMapping("/upload/images/{id}")
    public @ResponseBody
    void handleImagesUpload(@PathVariable long id, @RequestBody MultipartFile... images){
        for (MultipartFile image : images) {
            System.out.println("image" + image.getOriginalFilename());
            fileStorageService.storeImage(image, id);
        }
    }

    //To do: product id, not filename
    //("/files/product/{id}")
    @GetMapping("/files/download/product/{id}")
    public  @ResponseBody
    ResponseEntity<Resource> downloadFile(@PathVariable("id") long id) {

        System.out.println("HERE");
        Resource file = fileStorageService.loadAsResource(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}