package telerikacademy.extensionrepository.services.base;

import telerikacademy.extensionrepository.models.User;

import java.util.List;

public interface AdminService {
    void changeUserStatus(boolean status, long id);

    void changeUserStatus(boolean status, String username);

    void approveProduct(long id);

    User getById(long id);

    List<User> listAllAdmins();
}
