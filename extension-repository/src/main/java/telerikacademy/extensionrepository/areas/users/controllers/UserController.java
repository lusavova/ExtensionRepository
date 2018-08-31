package telerikacademy.extensionrepository.areas.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telerikacademy.extensionrepository.areas.products.models.Product;
import telerikacademy.extensionrepository.areas.users.exeptions.UserNotFoundExeption;
import telerikacademy.extensionrepository.areas.users.models.User;
import telerikacademy.extensionrepository.areas.users.services.base.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseBody
    public List<User> listAllUsers(){
        return userService.listAllUsers();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public User findById(@PathVariable("id") long id){
        return userService.findById(id);
    }

    @PutMapping(value = "/update/{id}")
    @ResponseBody
    public User updateUser(@RequestBody User updateUser, @PathVariable("id") long id){
        return userService.updateUser(id, updateUser);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deleteUser(@PathVariable long id){
        userService.deleteUser(id);
        return "Successfully deleted!";
    }

    @GetMapping("/products/{id}")
    @ResponseBody
    public List<Product> listAllUserProducts(@PathVariable("id") long id){
        return userService.listAllProducts(id);
    }

    @ExceptionHandler(UserNotFoundExeption.class)
    public String catchUserNotFoundExeption(){
        return "User Not Found.";
    }
}
