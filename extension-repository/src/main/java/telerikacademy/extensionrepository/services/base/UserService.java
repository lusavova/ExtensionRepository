package telerikacademy.extensionrepository.services.base;

import telerikacademy.extensionrepository.models.Product;
import telerikacademy.extensionrepository.models.User;

import java.util.List;

public interface UserService {

    List<User> listAllUsers();

    User getUserById(long id);

    User getUserByUsername(String username);

    User addUser(User user);

    User updateUser(User user);

    void deleteUser(long id);
}