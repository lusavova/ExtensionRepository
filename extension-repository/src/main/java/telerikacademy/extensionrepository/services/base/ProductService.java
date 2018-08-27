package telerikacademy.extensionrepository.services.base;

import telerikacademy.extensionrepository.models.DTO.ProductDTO;
import telerikacademy.extensionrepository.models.Product;

import java.util.List;

public interface ProductService {

    List<Product> listAllProducts();

    Product findById(long id);

    Product addProduct(ProductDTO product);

    Product updateProduct(long id, Product updateProduct);

    void deleteProduct(long id);

    List<Product> findAllSortedByName();

    List<Product> findAllSortedByNumberOfDownloadsDesc();

    List<Product> findAllSortedByUploadDateDesc();

    List<Product> findAllSortedByLastCommitDateDesc();

    List<Product> findTop10SortedByNumberOfDownloadsDesc();

    List<Product> findTop10SortedByUploadDateDesc();
}
