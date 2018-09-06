package telerikacademy.extensionrepository.areas.files.validator;

import org.springframework.web.multipart.MultipartFile;
import telerikacademy.extensionrepository.areas.files.exeptions.StorageException;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;

public class ImageValidator {

    public void checkImage(MultipartFile file) {
        try (InputStream input = file.getInputStream()) {
            try {
                //only BMP, GIF, JPG and PNG
                ImageIO.read(input).toString();
            } catch (Exception e) {
                System.out.println("NOT AN IMAGE");
               throw new StorageException("Not an image!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
