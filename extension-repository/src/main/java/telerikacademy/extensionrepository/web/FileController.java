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
public class FileController {
    private final FileStorageService fileStorageService;

    @Autowired
    public FileController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/file/{id}/add")
    public @ResponseBody
    void handleFileUpload(@RequestBody MultipartFile file, @PathVariable long id) {
        System.out.println("FILENAME: " + file.getOriginalFilename());
        fileStorageService.store(file, id);
    }


    @GetMapping("/files/{id}/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename ,@PathVariable("id") long id) {

        Resource file = fileStorageService.loadAsResource(id, filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}