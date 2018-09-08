package telerikacademy.extensionrepository.areas.users.services.base;

import telerikacademy.extensionrepository.areas.products.models.Product;
import telerikacademy.extensionrepository.areas.users.models.User;
import telerikacademy.extensionrepository.areas.users.models.UserDTO;
import telerikacademy.extensionrepository.common.FieldValueExists;

import java.util.List;

public interface UserService extends FieldValueExists {

    User findById(long id);

    User addUser(UserDTO user);

    User updateUser(UserDTO updateUser);

    void deleteUser(long id);

    List<User> listAllUsers();

    List<User> listAllBlockedUsers();

    List<User> listAllActiveUsers();

    List<Product> listAllProducts(long id);

    boolean usernameAlreadyExists(String username);

    boolean emailAlreadyExists(String email);

    void changeUserStatus(long id, String status);
}
