package telerikacademy.extensionrepository.areas.products.services.base;

import telerikacademy.extensionrepository.areas.products.models.dto.ProductDTO;
import telerikacademy.extensionrepository.areas.products.models.Product;
import telerikacademy.extensionrepository.common.FieldValueExists;

import java.util.List;

public interface ProductService extends FieldValueExists {
    List<Product> listAllProducts();

    Product findById(long id);

    Product addProduct(ProductDTO product);

    Product updateProduct(long id, Product updateProduct);

    void deleteProduct(long id);
}
