package telerikacademy.extensionrepository.areas.admin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telerikacademy.extensionrepository.areas.products.services.base.ProductService;
import telerikacademy.extensionrepository.areas.products.models.Product;
import telerikacademy.extensionrepository.areas.users.models.User;
import telerikacademy.extensionrepository.areas.admin.services.base.AdminService;
import telerikacademy.extensionrepository.areas.users.services.base.UserService;
import telerikacademy.extensionrepository.constants.Constants;

@Service
public class AdminServiceImpl implements AdminService {
    private UserService userService;
    private ProductService productService;

    @Autowired
    public AdminServiceImpl(ProductService productService,
                            UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @Override
    public void changeUserStatus(String status, long id) {
        User user = userService.findById(id);
        user.setUserStatus(status);
    }

    @Override
    public void approveProduct(long id) {
        Product product = productService.findById(id);
        product.setProductState(Constants.APPROVED_USER_STATUS);
    }
}
