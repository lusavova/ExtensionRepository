package telerikacademy.extensionrepository.areas.products.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProductNotFoundExeption extends RuntimeException {
    public ProductNotFoundExeption(String message) {
        super(message);
    }

    public ProductNotFoundExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
