package telerikacademy.daredevil.extensionrepository.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import telerikacademy.daredevil.extensionrepository.models.User;
import telerikacademy.daredevil.extensionrepository.services.base.UsersService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UsersService usersService;

    @Autowired
    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("")
    public List<User> listAllUsers() {
        return usersService.listAllUsers();
    }
}
