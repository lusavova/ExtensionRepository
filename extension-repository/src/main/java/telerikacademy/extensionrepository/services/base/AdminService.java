package telerikacademy.extensionrepository.services.base;

public interface AdminService {
    void changeUserStatus(boolean status, long id);

    void changeUserStatus(boolean status, String username);
}
