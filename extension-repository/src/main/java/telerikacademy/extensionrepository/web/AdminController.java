package telerikacademy.extensionrepository.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telerikacademy.extensionrepository.models.Product;
import telerikacademy.extensionrepository.models.User;
import telerikacademy.extensionrepository.services.base.AdminService;
import telerikacademy.extensionrepository.services.base.ProductService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;
    private ProductService productService;

    @Autowired
    public AdminController(AdminService adminService, ProductService productService) {
        this.adminService = adminService;
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public @ResponseBody User getById(@PathVariable("id") long id){
        return adminService.getById(id);
    }

    @PutMapping("/products/approve/{id}")
    public @ResponseBody
    void approveProduct(@PathVariable("id") long id) {
        adminService.approveProduct(id);
    }

    @DeleteMapping("/products/delete/{id}")
    public @ResponseBody void deleteProduct(@PathVariable("id") long id){
        productService.deleteProduct(id);
    }

    @PutMapping("/products/edit/{id}")
    public @ResponseBody void updateProduct(@PathVariable("id") long id, Product updateProduct){
        productService.updateProduct(id, updateProduct);
    }

    @PutMapping("/users/{id}")
    public @ResponseBody
    void changeUserStatus(@PathVariable("id") long id, @RequestParam String status) {

        switch (status) {
            case "Enable":
                adminService.changeUserStatus(true, id);
                break;
            case "Disable":
                adminService.changeUserStatus(false, id);
                break;
        }
    }
}
