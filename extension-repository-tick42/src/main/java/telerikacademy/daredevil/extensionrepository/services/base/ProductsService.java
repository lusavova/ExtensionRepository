package telerikacademy.daredevil.extensionrepository.services.base;

import telerikacademy.daredevil.extensionrepository.models.Product;

import java.util.List;

public interface ProductsService {

    Product findProductById(long id);

    List<Product> listAllProducts();

    void saveProductIntoDB(Product product);

    void updateProduct(long id, Product product);

    void deleteProduct(long id);

}
