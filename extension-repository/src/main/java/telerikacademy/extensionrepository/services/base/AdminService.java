package telerikacademy.extensionrepository.services.base;

import telerikacademy.extensionrepository.models.User;

import java.util.List;

public interface AdminService {
    void changeUserStatus(String status, long id);

    void approveProduct(long id);

    User getById(long id);
}
