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
    public @ResponseBody User findById(@PathVariable("id") String idString){
        long id = Long.parseLong(idString);
        return userService.getUserById(id);
    }

    @GetMapping("/{username}")
    public @ResponseBody User getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

//    @PostMapping("/add")
//    public @ResponseBody User addUser(@RequestBody User user){
//        return userService.addUser(user);
//    }

    @PutMapping(value = "update")
    public @ResponseBody User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public @ResponseBody void deleteUser(@PathVariable long id){
        userService.deleteUser(id);
    }
}
