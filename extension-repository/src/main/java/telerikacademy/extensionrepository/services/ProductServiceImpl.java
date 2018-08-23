package telerikacademy.extensionrepository.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telerikacademy.extensionrepository.data.ProductsRepository;
import telerikacademy.extensionrepository.data.UserRepository;
import telerikacademy.extensionrepository.models.Product;
import telerikacademy.extensionrepository.models.User;
import telerikacademy.extensionrepository.services.base.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductsRepository productsRepository;
    private UserRepository userRepository;

    @Autowired
    public ProductServiceImpl(ProductsRepository productsRepository, UserRepository userRepository) {
        this.productsRepository = productsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Product> listAllProducts() {
        return productsRepository.findAll();
    }

    @Override
    public Product findById(long id) {
        //VALIDATION
        return productsRepository.findById(id).orElse(null);
    }

    @Override
    public Product addProduct(Product product) {
        return productsRepository.saveAndFlush(product);
    }

    @Override
    public Product updateProduct(long id, Product product) {
        return productsRepository.saveAndFlush(product);
    }

    @Override
    public void deleteProduct(long id) {
        productsRepository.deleteById(id);
    }

    @Override
    public List<Product> findAllUserProducts(long id) {
        //Validation
        User user = userRepository.findById(id).orElse(null);
        return productsRepository.findAllByOwner(user);
    }

    @Override
    public List<Product> findAllSortedByName() {
        return productsRepository.findAllByOrderByName();
    }

    @Override
    public List<Product> findAllSortedByNumberOfDownloadsDesc() {
        return productsRepository.findAllByOrderByNumberOfDownloadsDesc();
    }

    @Override
    public List<Product> findAllSortedByUploadDateDesc() {
        return findAllSortedByUploadDateDesc();
    }

    @Override
    public List<Product> findAllSortedByLastCommitDateDesc() {
        return findAllSortedByLastCommitDateDesc();
    }
}
