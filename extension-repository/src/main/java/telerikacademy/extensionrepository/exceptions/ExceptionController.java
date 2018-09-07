package telerikacademy.extensionrepository.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import telerikacademy.extensionrepository.areas.files.exeptions.StorageException;
import telerikacademy.extensionrepository.areas.files.exeptions.StorageFileNotFoundException;
import telerikacademy.extensionrepository.areas.products.exeptions.ProductNotFoundExeption;
import telerikacademy.extensionrepository.areas.tags.exeptions.TagNotFoundExeption;
import telerikacademy.extensionrepository.areas.users.exeptions.UserNotFoundExeption;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.NoSuchFileException;
import java.util.Date;

@ControllerAdvice
@RestController
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductNotFoundExeption.class)
    public final ResponseEntity<ErrorDetails> handleProductNotFoundExeption(ProductNotFoundExeption ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StorageException.class)
    public final ResponseEntity<ErrorDetails> handleStorageException(StorageException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleStorageFileNotFound(StorageFileNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<ErrorDetails> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FormatExeption.class)
    public final ResponseEntity<ErrorDetails> handleFormatExeption(FormatExeption ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MalformedURLException.class)
    public final ResponseEntity<ErrorDetails> handleMalformedURLException(MalformedURLException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchFileException.class)
    public final ResponseEntity<ErrorDetails> handleNoSuchFileException(NoSuchFileException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IOException.class)
    public final ResponseEntity<ErrorDetails> handleIOException(IOException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TagNotFoundExeption.class)
    public final ResponseEntity<ErrorDetails> handleTagNotFoundExeption(TagNotFoundExeption ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public final String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
//        return ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
//    }

    @ExceptionHandler(UserNotFoundExeption.class)
    public final ResponseEntity<ErrorDetails> handlecatchUserNotFoundExeption(UserNotFoundExeption ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }



//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public String handleValidationException(MethodArgumentNotValidException ex) {
//        return ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
//    }

}
