package telerikacademy.extensionrepository.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telerikacademy.extensionrepository.models.User;
import telerikacademy.extensionrepository.services.base.UserService;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public @ResponseBody User registerUser(@RequestBody User user){
        return userService.addUser(user);
    }
}
