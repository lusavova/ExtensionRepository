package telerikacademy.daredevil.extensionrepository.services.base;

import telerikacademy.daredevil.extensionrepository.models.Product;

import java.util.List;

public interface ProductsService {

    Product findProductById(long id);

    List<Product> listAllProducts();

    void saveProductIntoDB(Product product);

    void updateProduct(long id, Product product);

    void deleteProduct(long id);

    List<Product> getProductsOrderByNameAsc();

    List<Product> getMostPopularProducts();

    List<Product> getProductOrderByLastCommitDate();
//
//    List<Product> getNewestProducts();
//
//    //List<Product> getFeaturedProducts();
//
//    List<Product> getProductsByOwner(String ownerName);
//
//    //List<Product> getProductsOrderedByName(String productName);
//
//
//    //List<Product> orderProductsBy(Predicate criteria);
}
