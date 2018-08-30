package telerikacademy.extensionrepository.areas.users.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import telerikacademy.extensionrepository.areas.users.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
