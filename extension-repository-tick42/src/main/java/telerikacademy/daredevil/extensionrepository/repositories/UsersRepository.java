package telerikacademy.daredevil.extensionrepository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import telerikacademy.daredevil.extensionrepository.models.User;

public interface UsersRepository  extends JpaRepository<User, Long> {
}
