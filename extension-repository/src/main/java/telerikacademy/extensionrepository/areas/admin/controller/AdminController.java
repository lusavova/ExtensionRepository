package telerikacademy.extensionrepository.areas.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telerikacademy.extensionrepository.areas.products.models.Product;
import telerikacademy.extensionrepository.areas.admin.services.base.AdminService;
import telerikacademy.extensionrepository.areas.products.services.base.ProductService;

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

    @PutMapping("/products/approve/{id}")
    public @ResponseBody
    String approveProduct(@PathVariable("id") long id) {
        adminService.approveProduct(id);
        return "Successfully approved!";
    }

    @DeleteMapping("/products/delete/{id}")
    public @ResponseBody
    String deleteProduct(@PathVariable("id") long id) {
        productService.deleteProduct(id);
        return "Successfully deleted!";
    }

    @PutMapping("/products/edit/{id}")
    public @ResponseBody
    String editProduct(@PathVariable("id") long id, Product product) {
        productService.updateProduct(id, product);
        return "Successfully updated!";
    }

    @PutMapping("/users/{id}")
    public @ResponseBody
    String changeUserStatus(@PathVariable("id") long id, @RequestParam String status) {
        switch (status) {
            case "Enable":
                adminService.changeUserStatus(status, id);
                return "Enabled user";
            case "Disable":
                adminService.changeUserStatus(status, id);
                return "Disabled user";
        }
        return "Wrong status!";
    }
}
