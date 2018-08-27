package telerikacademy.extensionrepository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import telerikacademy.extensionrepository.models.Product;
import telerikacademy.extensionrepository.models.User;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ProductsRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByOrderByName();

    List<Product> findAllByOrderByNumberOfDownloadsDesc();

    List<Product> findAllByOrderByUploadDateDesc();

    List<Product> findAllByOrderByLastCommitDateDesc();

    List<Product> findTop10ByOrderByNumberOfDownloadsDesc();

    List<Product> findTop10ByOrderByUploadDateDesc();

//    @Modifying
//    @Query("update Product as p set p.productState = 'approved' where p.id =?1")
//    void approveProduct(long id);
}