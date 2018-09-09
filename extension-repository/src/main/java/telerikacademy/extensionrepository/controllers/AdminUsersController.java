package telerikacademy.extensionrepository.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telerikacademy.extensionrepository.enums.Roles;
import telerikacademy.extensionrepository.models.Product;
import telerikacademy.extensionrepository.models.User;
import telerikacademy.extensionrepository.services.base.ProductService;
import telerikacademy.extensionrepository.services.base.UserService;
import telerikacademy.extensionrepository.enums.UserStatus;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/users")
public class AdminUsersController {
    private UserService userService;
    private ProductService productService;

    @Autowired
    public AdminUsersController(UserService userService,
                                ProductService productService) {
        this.userService = userService;
        this.productService = productService;
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

    @PostMapping("/enableUser/{id}")
    @ResponseBody
    public void enableUser(@PathVariable("id") long id) {
        userService.changeUserStatus(id, UserStatus.ENABLED.name());
    }

    @PostMapping("/disableUser/{id}")
    @ResponseBody
    public void disableUser(@PathVariable("id") long id) {
        userService.changeUserStatus(id, UserStatus.DISABLED.name());
    }

    @PostMapping("/changeRole/admin/{id}")
    @ResponseBody
    public void makeUserAdmin(@PathVariable("id") long id) {
        userService.changeUserRole(id, Roles.ADMIN.name());
    }

    @GetMapping("/listAllUserProducts/{id}")
    public List<Product> listAllUserProducts(@PathVariable long id) {
        return productService.listAllProducts()
                .stream()
                .filter(p->p.getOwner().getId() == id)
                .collect(Collectors.toList());
    }
}
