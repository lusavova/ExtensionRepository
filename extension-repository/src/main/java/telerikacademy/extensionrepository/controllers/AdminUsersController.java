package telerikacademy.extensionrepository.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telerikacademy.extensionrepository.enums.Roles;
import telerikacademy.extensionrepository.models.User;
import telerikacademy.extensionrepository.services.base.UserService;
import telerikacademy.extensionrepository.enums.UserStatus;

import java.util.List;

@RestController
@RequestMapping("/admin/users")
public class AdminUsersController {
    private UserService userService;

    @Autowired
    public AdminUsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/listAll")
    public List<User> listAll() {
        return userService.listAllUsers();
    }

    @GetMapping("/listAll/blocked")
    public List<User> listAllBlockedUsers() {
        return userService.listAllBlockedUsers();
    }

    @GetMapping("/listAll/active")
    public List<User> listAllActiveUsers() {
        return userService.listAllActiveUsers();
    }

    @PutMapping("/enableUser/{id}")
    @ResponseBody
    public void enableUser(@PathVariable("id") long id) {
        userService.changeUserStatus(id, UserStatus.ENABLED.name());
    }

    @PutMapping("/disableUser/{id}")
    @ResponseBody
    public void disableUser(@PathVariable("id") long id) {
        userService.changeUserStatus(id, UserStatus.DISABLED.name());
    }

    @PutMapping("/changeRole/admin/{id}")
    @ResponseBody
    public void makeUserAdmin(@PathVariable("id") long id) {
        userService.changeUserRole(id, Roles.ADMIN.name());
    }
}
