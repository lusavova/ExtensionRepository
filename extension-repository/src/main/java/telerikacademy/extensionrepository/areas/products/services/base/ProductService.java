package telerikacademy.extensionrepository.areas.products.services.base;

import telerikacademy.extensionrepository.areas.products.models.dto.ProductDTO;
import telerikacademy.extensionrepository.areas.products.models.Product;
import telerikacademy.extensionrepository.common.FieldValueExists;

import java.util.List;

public interface ProductService extends FieldValueExists {
    Product findById(long id);

    Product addProduct(ProductDTO product);

    Product updateProduct(ProductDTO updateProduct);

    void deleteProduct(long id);

    List<Product> listAllProducts();

    List<Product> listAllDisabledProducts();

    List<Product> listAllActiveProducts();

    List<Product> listAllPendingProducts();

    void changeProductStatus(long id, String status);

    void changeFeatureProductStatus(long id, boolean status);

    boolean githubRepoAlreadyExists(String account);
}
