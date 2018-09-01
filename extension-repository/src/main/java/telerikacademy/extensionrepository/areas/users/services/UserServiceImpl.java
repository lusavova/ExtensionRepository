package telerikacademy.extensionrepository.areas.users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telerikacademy.extensionrepository.areas.products.models.Product;
import telerikacademy.extensionrepository.areas.users.data.UserRepository;
import telerikacademy.extensionrepository.areas.users.exeptions.UserNotFoundExeption;
import telerikacademy.extensionrepository.areas.users.models.UserDTO;
import telerikacademy.extensionrepository.areas.users.validators.UserValidator;
import telerikacademy.extensionrepository.areas.users.models.User;
import telerikacademy.extensionrepository.areas.users.services.base.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final String PENDING_USER_STATUS = "pending";

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundExeption(
                        String.format("Can't find user with id = %d", id)));
    }

    @Override
    public User addUser(UserDTO userDTO) {
        User user = bindUserDTOtoUser(userDTO);
        user.setUserStatus(PENDING_USER_STATUS);
        checkUser(user);
        return userRepository.save(user);
    }

    private void checkUser(User user) {
        UserValidator userValidator = new UserValidator();
        userValidator.checkUserDataFormat(user);

        List<User> users = listAllUsers();

        for (User u : users) {
            if (u.getEmail().equals(user.getEmail())){
                throw new IllegalArgumentException("Email already exists.");
            }
            if (u.getUsername().equals(user.getUsername())){
                throw new IllegalArgumentException("Username already exists.");
            }
        }
    }

    @Override
    public User updateUser(long id, User updateUser) {
        findById(id);
        return userRepository.save(updateUser);
    }

    @Override
    public void deleteUser(long id) {
        findById(id);
        userRepository.deleteById(id);
    }

    @Override
    public List<Product> listAllProducts(long id) {
        List<Product> products = findById(id).getProducts();
        return products;
    }

    private User bindUserDTOtoUser(UserDTO userDTO){
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setProducts(userDTO.getProducts());
        return user;
    }
}