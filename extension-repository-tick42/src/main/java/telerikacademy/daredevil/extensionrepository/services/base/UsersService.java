package telerikacademy.daredevil.extensionrepository.services.base;

import telerikacademy.daredevil.extensionrepository.models.User;

import java.util.List;

public interface UsersService {
    List<User> listAllUsers();

    User findUserById(long id);

    User findUserByUsername(String username);

    void saveUserIntoDB(User user);

    void deleteUser(long id);

    void updateUser(int id, User updateUser);

}
