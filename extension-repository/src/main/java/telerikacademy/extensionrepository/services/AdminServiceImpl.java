package telerikacademy.extensionrepository.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telerikacademy.extensionrepository.data.ProductsRepository;
import telerikacademy.extensionrepository.data.UserRepository;
import telerikacademy.extensionrepository.exceptions.NoSuchUserExeption;
import telerikacademy.extensionrepository.models.Product;
import telerikacademy.extensionrepository.models.User;
import telerikacademy.extensionrepository.services.base.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
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
