package telerikacademy.daredevil.extensionrepository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import telerikacademy.daredevil.extensionrepository.models.Product;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Product, Long>{

    List<Product> findAllByOrderByNumberOfDownloadsDesc();

    List<Product> findAllByOrderByUploadDateDesc();

    //List<Product> findByOwner(User owner);
}
