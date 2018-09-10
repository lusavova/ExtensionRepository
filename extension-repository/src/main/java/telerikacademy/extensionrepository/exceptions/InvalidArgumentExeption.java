package telerikacademy.extensionrepository.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidArgumentExeption extends RuntimeException {
    public InvalidArgumentExeption(String message) {
        super(message);
    }

    public InvalidArgumentExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
