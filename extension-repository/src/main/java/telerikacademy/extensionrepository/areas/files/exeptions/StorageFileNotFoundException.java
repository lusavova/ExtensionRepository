package telerikacademy.extensionrepository.areas.files.exeptions;

import telerikacademy.extensionrepository.areas.files.exeptions.StorageException;

public class StorageFileNotFoundException extends StorageException {

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}