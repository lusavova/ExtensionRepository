package telerikacademy.daredevil.extensionrepository.data.base;

import telerikacademy.daredevil.extensionrepository.models.Product;

import java.util.List;

public interface ProductRepository {

    Product getById(int id);

    List<Product> listAllProducts();

    void addProduct(Product product);

    void updateProducts(int id, Product product);

    void removeProduct(int id);

    List<Product> getProductsSortedByName();

    List<Product> getProductsSortedByNumOfDownloads();

    List<Product> getProductsSortedByUploadDate();

    List<Product> getProductsSortedByLastCommitDate();
}
