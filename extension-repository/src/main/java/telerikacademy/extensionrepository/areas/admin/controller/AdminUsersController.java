package telerikacademy.extensionrepository.areas.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telerikacademy.extensionrepository.areas.users.models.User;
import telerikacademy.extensionrepository.areas.users.services.base.UserService;
import telerikacademy.extensionrepository.areas.users.enums.UserStatus;

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
}
