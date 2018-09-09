package telerikacademy.extensionrepository.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import telerikacademy.extensionrepository.services.base.StorageService;
import telerikacademy.extensionrepository.models.Product;
import telerikacademy.extensionrepository.models.User;
import telerikacademy.extensionrepository.services.base.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private StorageService storageService;

    @Autowired
    public UserController(UserService userService,
                          StorageService storageService) {
        this.userService = userService;
        this.storageService = storageService;
    }

    @GetMapping
    @ResponseBody
    public List<User> listAllUsers() {
        return userService.listAllActiveUsers();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public User findById(@PathVariable long id) {
        return userService.findById(id);
    }

    @PutMapping(value = "/update/{id}")
    @ResponseBody
    public User updateUser(@RequestBody @Valid User updateUser, @PathVariable long id) {
        return userService.updateUser(updateUser, id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        storageService.deleteAllUserFilesFromSystem(userService.findById(id));
        return "Successfully deleted!";
    }

    @GetMapping("/products/{id}")
    @ResponseBody
    public List<Product> listAllUserProducts(@PathVariable("id") long id) {
        return userService.listAllProducts(id);
    }

    @PostMapping("/user/username")
    @ResponseBody
    public boolean usernameAlreadyExists(String username) {
        return userService.usernameAlreadyExists(username);
    }

    @GetMapping("/user/email")
    @ResponseBody
    public boolean emailAlreadyExists(String email) {
        return userService.emailAlreadyExists(email);
    }

    @ExceptionHandler
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
    }
}
