package telerikacademy.extensionrepository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import telerikacademy.extensionrepository.models.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("select u from User as u where u.userStatus = 'DISABLED'")
    List<User> listAllBlockedUsers();

    @Query("select u from User as u where u.userStatus = 'ENABLED'")
    List<User> listAllActiveUsers();
}
