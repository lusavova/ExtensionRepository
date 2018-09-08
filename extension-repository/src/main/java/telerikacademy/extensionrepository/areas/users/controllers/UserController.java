package telerikacademy.extensionrepository.areas.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import telerikacademy.extensionrepository.areas.files.services.base.StorageService;
import telerikacademy.extensionrepository.areas.products.models.Product;
import telerikacademy.extensionrepository.areas.users.models.User;
import telerikacademy.extensionrepository.areas.users.models.UserDTO;
import telerikacademy.extensionrepository.areas.users.services.base.UserService;

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
    public List<User> listAllUsers(){
        return userService.listAllUsers();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public User findById(@PathVariable long id){
        return userService.findById(id);
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public User updateUser(@RequestBody UserDTO updateUser){
        return userService.updateUser(updateUser);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deleteUser(@PathVariable long id){
        userService.deleteUser(id);
        storageService.deleteAllUserFilesFromSystem(userService.findById(id));
        return "Successfully deleted!";
    }

    @GetMapping("/products/{id}")
    @ResponseBody
    public List<Product> listAllUserProducts(@PathVariable("id") long id){
        return userService.listAllProducts(id);
    }

    @PostMapping("/user/username")
    @ResponseBody
    public boolean usernameAlreadyExists(String username){
        return userService.usernameAlreadyExists(username);
    }

    @GetMapping("/user/email")
    @ResponseBody
    public boolean emailAlreadyExists(String email){
        return userService.emailAlreadyExists(email);
    }

    @ExceptionHandler
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String message =  ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return message;
    }
}
