package telerikacademy.extensionrepository.areas.admin.services.base;

public interface AdminService {
    void changeUserStatus(String status, long id);

    void approveProduct(long id);
}
