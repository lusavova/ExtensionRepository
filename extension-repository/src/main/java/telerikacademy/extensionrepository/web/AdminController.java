package telerikacademy.extensionrepository.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telerikacademy.extensionrepository.services.base.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // getById
    


    // approve extension (/admin/products/approve)



    // deleteExtension (/admin/products/delete/{name})



    // edit extension (/admin/products/edit/{id})



    // enable-disable user (/admin/users/{id})
    @PutMapping("/users/{id}")
    public @ResponseBody void changeUserStatus(@PathVariable("id") long id, @RequestParam String status) {

        switch (status) {
            case "Enable":
                adminService.changeUserStatus(true, id);
                break;
            case "Disable":
                adminService.changeUserStatus(false, id);
                break;
        }
    }

    @PutMapping("/users/{username}")
    public @ResponseBody void changeUserStatus(@PathVariable("username") String username, @RequestParam String status) {
        switch (status) {
            case "Enable":
                adminService.changeUserStatus(true, username);
                break;
            case "Disable":
                adminService.changeUserStatus(false, username);
                break;
        }
    }
}
