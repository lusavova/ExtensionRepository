package telerikacademy.extensionrepository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import telerikacademy.extensionrepository.models.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

}
