package telerikacademy.extensionrepository.areas.products.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import telerikacademy.extensionrepository.areas.products.models.Product;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByOrderByName();

//    @Query("select p from Product p inner join User u on u.id = p.owner where u.userStatus = 'ENABLED'")

    @Query("select p from Product p where p.productStatus = 'DISABLED'")
    List<Product> listAllDisabledProducts();

    @Query("select p from Product p where p.productStatus = 'ENABLED'")
    List<Product> listAllActiveProducts();

    @Query("select p from Product p where p.productStatus = 'PENDING'")
    List<Product> listAllPendingProducts();

    List<Product> findAllByOrderByNumberOfDownloadsDesc();

    List<Product> findAllByOrderByUploadDateDesc();

    List<Product> findAllByOrderByLastCommitDateDesc();

    List<Product> findTop10ByOrderByNumberOfDownloadsDesc();

    List<Product> findTop10ByOrderByUploadDateDesc();
}