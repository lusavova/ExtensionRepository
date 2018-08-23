package telerikacademy.extensionrepository.services.base;

import telerikacademy.extensionrepository.models.Product;

import java.util.List;

public interface ProductService {

    List<Product> listAllProducts();

    Product findById(long id);

    Product findByName(String name);

    Product addProduct(Product product);

    Product updateProduct(long id, Product product);

    void deleteProduct(long id);

    List<Product> findAllUserProducts(long id);

    List<Product> findAllSortedByName();

    List<Product> findAllSortedByNumberOfDownloadsDesc();

    List<Product> findAllSortedByUploadDateDesc();

    List<Product> findAllSortedByLastCommitDateDesc();

    List<Product> findTop10SortedByNumberOfDownloadsDesc();

    List<Product> findTop10SortedByUploadDateDesc();
}
