package telerikacademy.extensionrepository.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telerikacademy.extensionrepository.models.User;
import telerikacademy.extensionrepository.services.base.UserService;

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
    public List<User> listAllUsers(){
        return userService.listAllUsers();
    }

    @GetMapping(value = "/{id}")
    public @ResponseBody User findById(@PathVariable("id") long id){
        return userService.getUserById(id);
    }

    @PutMapping(value = "/update")
    public @ResponseBody User updateUser(@RequestBody User updateUser){
        return userService.updateUser(updateUser);
    }

    @DeleteMapping("/delete/{id}")
    public @ResponseBody String deleteUser(@PathVariable long id){
        userService.deleteUser(id);
        return "Successfully deleted!";
    }
}
