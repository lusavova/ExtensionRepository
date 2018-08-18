package telerikacademy.daredevil.extensionrepository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import telerikacademy.daredevil.extensionrepository.models.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
