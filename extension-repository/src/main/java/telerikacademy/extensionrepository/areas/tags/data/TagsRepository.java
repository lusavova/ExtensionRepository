package telerikacademy.extensionrepository.areas.tags.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import telerikacademy.extensionrepository.areas.tags.models.Tag;

@Repository
public interface TagsRepository extends JpaRepository<Tag, Long> {
    Tag findTagByTagname(String tagname);
}
