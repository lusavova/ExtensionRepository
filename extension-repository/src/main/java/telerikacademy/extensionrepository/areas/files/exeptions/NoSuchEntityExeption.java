package telerikacademy.extensionrepository.areas.files.exeptions;

public class NoSuchEntityExeption extends RuntimeException {
    public NoSuchEntityExeption(String message) {
        super(message);
    }

    public NoSuchEntityExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
