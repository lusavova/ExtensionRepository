package telerikacademy.extensionrepository.exceptions;

public class NoSuchEntityExeption extends RuntimeException {
    public NoSuchEntityExeption(String message) {
        super(message);
    }

    public NoSuchEntityExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
