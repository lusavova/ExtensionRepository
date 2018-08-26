package telerikacademy.extensionrepository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import telerikacademy.extensionrepository.models.Tag;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TagsRepository extends JpaRepository<Tag, Long> {
}
