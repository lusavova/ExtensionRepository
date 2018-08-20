package telerikacademy.daredevil.extensionrepository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import telerikacademy.daredevil.extensionrepository.models.Product;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Product, Long>{
    List<Product> findAllByOrderByNumberOfDownloadsDesc();

    List<Product> findAllByOrderByUploadDateDesc();

    List<Product> findAllByOrderByName();

    List<Product> findAllByOrderByNameDesc();

    @Query(value = "SELECT p, g FROM Product AS p " +
            "JOIN GithubInfo AS g " +
            "ON p.githubInfo = g.id " +
            "ORDER BY g.lastCommitDate DESC")
    List<Product> findAllSortedByLastCommitDate();
}
