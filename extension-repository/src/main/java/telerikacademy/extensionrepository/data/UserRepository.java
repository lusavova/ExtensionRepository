package telerikacademy.extensionrepository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import telerikacademy.extensionrepository.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Modifying
    @Query("update  User u set u.userStatus = ?1 where  u.id = ?2")
    void chandeUserStatus(boolean status, long id);

    @Modifying
    @Query("update  User u set u.userStatus = ?1 where  u.username = ?2")
    void chandeUserStatus(boolean status, String username);
}
