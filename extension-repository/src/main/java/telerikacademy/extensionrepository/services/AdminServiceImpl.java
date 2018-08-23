package telerikacademy.extensionrepository.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telerikacademy.extensionrepository.data.ProductsRepository;
import telerikacademy.extensionrepository.data.UserRepository;
import telerikacademy.extensionrepository.models.User;
import telerikacademy.extensionrepository.services.base.AdminService;

import javax.transaction.Transactional;

@Transactional
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
    public void changeUserStatus(boolean status, long id) {
        userRepository.changeUserStatus(status, id);
    }

    @Override
    public void changeUserStatus(boolean status, String username) {
        userRepository.changeUserStatus(status, username);
    }

    @Override
    public void approveProduct(long id) {
        productsRepository.approveProduct(id);
    }

    @Override
    public User getById(long id) {
        userRepository.findById(id);
        return null;
    }
}
