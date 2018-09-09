package telerikacademy.extensionrepository.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import telerikacademy.extensionrepository.exceptions.ErrorDetails;
import telerikacademy.extensionrepository.models.User;
import telerikacademy.extensionrepository.services.base.UserService;

import javax.security.auth.login.LoginException;
import java.util.Date;

@RestController
@RequestMapping("/login")
public class LoginController {
    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User loginUser(String username, String password) throws LoginException {
        User user = userService.findByUsername(username);
        if (user.getPassword().equals(password)){
            return user;
        } else {
            throw new LoginException("Incorrect username or password");
        }
    }
}
