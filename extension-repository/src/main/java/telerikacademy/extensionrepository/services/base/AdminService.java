package telerikacademy.extensionrepository.services.base;

import telerikacademy.extensionrepository.models.User;

public interface AdminService {
    void changeUserStatus(boolean status, long id);

    void changeUserStatus(boolean status, String username);

    void approveProduct(long id);

    User getById(long id);
}
