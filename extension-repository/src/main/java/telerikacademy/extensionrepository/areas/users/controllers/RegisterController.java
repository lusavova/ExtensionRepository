package telerikacademy.extensionrepository.areas.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import telerikacademy.extensionrepository.areas.users.models.User;
import telerikacademy.extensionrepository.areas.users.models.UserDTO;
import telerikacademy.extensionrepository.areas.users.services.base.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseBody
    public User registerUser(@RequestBody @Valid UserDTO user) {
        return userService.addUser(user);
    }
}
