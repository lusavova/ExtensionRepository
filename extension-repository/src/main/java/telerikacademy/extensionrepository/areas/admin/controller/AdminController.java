package telerikacademy.extensionrepository.areas.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telerikacademy.extensionrepository.areas.products.models.Product;
import telerikacademy.extensionrepository.areas.admin.services.base.AdminService;
import telerikacademy.extensionrepository.areas.products.services.base.ProductService;
import telerikacademy.extensionrepository.constants.Constants;
import telerikacademy.extensionrepository.enums.UserStatus;

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
    @ResponseBody
    public String approveProduct(@PathVariable("id") long id) {
        adminService.approveProduct(id);
        return "Successfully approved!";
    }

    @DeleteMapping("/products/delete/{id}")
    @ResponseBody
    public String deleteProduct(@PathVariable("id") long id) {
        productService.deleteProduct(id);
        return "Successfully deleted!";
    }

    @PutMapping("/products/edit/{id}")
    @ResponseBody
    public String editProduct(@PathVariable("id") long id, Product product) {
        productService.updateProduct(id, product);
        return "Successfully updated!";
    }

//    @PutMapping("/users/{id}")
//    @ResponseBody
//    public void changeUserStatus(@PathVariable("id") long id, @RequestParam String status) {
//        switch (status) {
//            case "Enable":
//                adminService.changeUserStatus(status, id);
//                break;
//            case "Disable":
//                adminService.changeUserStatus(status, id);
//                break;
//        }
//    }

    @PutMapping("/enableUser/{id}")
    @ResponseBody
    public void enableUser(@PathVariable("id") long id) {
        adminService.changeUserStatus(UserStatus.ENABLE.name(), id);
    }

    @PutMapping("/disableUser/{id}")
    @ResponseBody
    public void disableUser(@PathVariable("id") long id) {
        adminService.changeUserStatus(UserStatus.DISABLE.name(), id);
    }

    @PutMapping("/products/feature/{id}")
    @ResponseBody
    public void featureProduct(@PathVariable("id") long id) {
        adminService.featureProduct(id);
    }
}
