package telerikacademy.extensionrepository.areas.tags.exeptions;

public class TagNotFoundExeption extends RuntimeException {
    public TagNotFoundExeption(String message) {
        super(message);
    }
}
