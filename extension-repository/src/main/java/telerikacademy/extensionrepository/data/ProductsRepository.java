package telerikacademy.extensionrepository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import telerikacademy.extensionrepository.models.Product;

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

    @Query("select p from Product p where p.productStatus = 'ENABLED' order by p.numberOfDownloads desc ")
    List<Product> findAllProductsSortedByNumberOfDownloadsDesc();

    @Query("select p from Product p where p.productStatus = 'ENABLED' order by p.uploadDate desc ")
    List<Product> findAllProductsSortedByUploadDateDesc();

    @Query("select p from Product p where p.productStatus = 'ENABLED' order by p.lastCommitDate desc ")
    List<Product> findAllProductsSortedByLastCommitDateDesc();

    @Query("select p from Product p where p.featuredProduct = true order by p.uploadDate desc")
    List<Product> findAllFeaturedProducts();
}