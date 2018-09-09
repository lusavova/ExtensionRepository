package telerikacademy.extensionrepository.services.base;

import telerikacademy.extensionrepository.models.Product;
import telerikacademy.extensionrepository.models.User;
import telerikacademy.extensionrepository.models.dto.UserDTO;
import telerikacademy.extensionrepository.common.FieldValueExists;

import java.util.List;

public interface UserService extends FieldValueExists {

    User findById(long id);

    User findByUsername(String username);

    User addUser(UserDTO user);

    User updateUser(User updateUser, long id);

    void deleteUser(long id);

    List<User> listAllUsers();

    List<User> listAllBlockedUsers();

    List<User> listAllActiveUsers();

    List<Product> listAllProducts(long id);

    boolean usernameAlreadyExists(String username);

    boolean emailAlreadyExists(String email);

    void changeUserStatus(long id, String status);

    void changeUserRole(long id, String role);
}
