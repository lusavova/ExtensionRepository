package telerikacademy.extensionrepository.areas.files.exeptions;

public class NoSuchUserExeption extends RuntimeException {
    public NoSuchUserExeption(String message) {
        super(message);
    }

    public NoSuchUserExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
