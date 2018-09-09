package telerikacademy.extensionrepository.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import telerikacademy.extensionrepository.models.User;
import telerikacademy.extensionrepository.models.dto.UserDTO;
import telerikacademy.extensionrepository.services.base.UserService;

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


    @ExceptionHandler
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String message =  ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return message;
    }
}
