package telerikacademy.extensionrepository.exceptions;

public class NoSuchUserExeption extends RuntimeException {
    public NoSuchUserExeption(String message) {
        super(message);
    }

    public NoSuchUserExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
