package telerikacademy.extensionrepository.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telerikacademy.extensionrepository.models.User;
import telerikacademy.extensionrepository.services.base.UserService;

import javax.security.auth.login.LoginException;

@RestController
@RequestMapping("/login")
public class LoginController {
    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseBody
    public User loginUser(@RequestBody String username, String password) throws LoginException {
        User user = userService.findByUsername(username);
        if (user.getPassword().equals(password)){
            return user;
        } else {
            throw new LoginException("Incorrect username or password");
        }
    }
}
