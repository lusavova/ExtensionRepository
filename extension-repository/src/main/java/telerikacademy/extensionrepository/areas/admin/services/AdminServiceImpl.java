package telerikacademy.extensionrepository.areas.admin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telerikacademy.extensionrepository.areas.products.data.ProductsRepository;
import telerikacademy.extensionrepository.areas.users.data.UserRepository;
import telerikacademy.extensionrepository.areas.files.exeptions.NoSuchUserExeption;
import telerikacademy.extensionrepository.areas.products.models.Product;
import telerikacademy.extensionrepository.areas.users.models.User;
import telerikacademy.extensionrepository.areas.admin.services.base.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
    private static final String APPROVED_USER_STATUS = "approved";

    private UserRepository userRepository;
    private ProductsRepository productsRepository;

    @Autowired
    public AdminServiceImpl(UserRepository userRepository, ProductsRepository productsRepository) {
        this.userRepository = userRepository;
        this.productsRepository = productsRepository;
    }

    @Override
    public void changeUserStatus(String status, long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchUserExeption("Cannot find user with id = " + id));
        user.setUserStatus(status);
    }

    @Override
    public void approveProduct(long id) {
        Product product = productsRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchUserExeption(String.format("Can't find user with id = %d", id)));
        product.setProductState("approved");
    }
}
