package telerikacademy.extensionrepository.validator;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;

public class ImageValidator {

    public boolean validateImage(MultipartFile file) {
        try (InputStream input = file.getInputStream()) {
            try {
                ImageIO.read(input).toString();
                // It's an image (only BMP, GIF, JPG and PNG are recognized).
                System.out.println("is an image");
                return true;
            } catch (Exception e) {
                System.out.println("is not an image");
                return false;
                // It's not an image.
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
