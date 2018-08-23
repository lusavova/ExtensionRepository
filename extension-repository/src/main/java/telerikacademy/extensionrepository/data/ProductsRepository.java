package telerikacademy.extensionrepository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import telerikacademy.extensionrepository.models.Product;
import telerikacademy.extensionrepository.models.User;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ProductsRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByOwner(User owner);

    @Query("SELECT p FROM Product as p ORDER BY name")
    List<Product> getAllOrderByName();

    List<Product> findAllByOrderByNumberOfDownloadsDesc();

    List<Product> findAllByOrderByUploadDateDesc();

    List<Product> findAllByOrderByLastCommitDateDesc();

    List<Product> findTop10ByOrderByNumberOfDownloadsDesc();

    List<Product> findTop10ByOrderByUploadDateDesc();
}