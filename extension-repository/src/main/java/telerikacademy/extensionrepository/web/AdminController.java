package telerikacademy.extensionrepository.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telerikacademy.extensionrepository.models.Product;
import telerikacademy.extensionrepository.models.User;
import telerikacademy.extensionrepository.services.base.AdminService;
import telerikacademy.extensionrepository.services.base.ProductService;

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
    public @ResponseBody
    User getById(@PathVariable("id") long id) {
        return adminService.getById(id);
    }


    //•	Administrators COULD approve extension
    @PutMapping("/products/approve/{id}")
    public @ResponseBody
    String approveProduct(@PathVariable("id") long id) {
        adminService.approveProduct(id);
        return "Successfully approved!";
    }

    //•	Administrators COULD delete/edit all extensions
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
