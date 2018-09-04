package telerikacademy.extensionrepository.areas.users.services.base;

import telerikacademy.extensionrepository.areas.products.models.Product;
import telerikacademy.extensionrepository.areas.users.models.User;
import telerikacademy.extensionrepository.areas.users.models.UserDTO;
import telerikacademy.extensionrepository.common.FieldValueExists;

import java.util.List;

public interface UserService extends FieldValueExists {
    List<User> listAllUsers();

    User find(String username);

    User findById(long id);

    User addUser(UserDTO user);

    User updateUser(long id, User updateUser);

    void deleteUser(long id);

    List<Product> listAllProducts(long id);
}
