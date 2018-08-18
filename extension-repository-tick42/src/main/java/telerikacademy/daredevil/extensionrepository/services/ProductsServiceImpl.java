package telerikacademy.daredevil.extensionrepository.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telerikacademy.daredevil.extensionrepository.models.Product;
import telerikacademy.daredevil.extensionrepository.repositories.ProductsRepository;
import telerikacademy.daredevil.extensionrepository.services.base.ProductsService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductsServiceImpl implements ProductsService {
    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public Product findProductById(long id){
        // VALIDATION????
        Product product = productsRepository.findById(id).orElse(null);
        return product;
    }

    @Override
    public List<Product> listAllProducts() {
        return productsRepository.findAll();
    }

    @Override
    public void saveProductIntoDB(Product product) {
        this.productsRepository.saveAndFlush(product);
    }

    @Override
    public void updateProduct(long id, Product updateProduct) {
        //?????
        Product product = productsRepository.getOne(id);
        product.setName(updateProduct.getName());
        product.setDescription(updateProduct.getDescription());
        product.setVersion(updateProduct.getVersion());
        product.setNumberOfDownloads(updateProduct.getNumberOfDownloads());
        product.setDownloadLink(updateProduct.getDownloadLink());
        product.setOwner(updateProduct.getOwner());
        product.setGithubInfo(updateProduct.getGithubInfo());
        product.setTags(updateProduct.getTags());
        productsRepository.saveAndFlush(product);
    }

    @Override
    public void deleteProduct(long id) {
        productsRepository.deleteById(id);
    }

    @Override
    public List<Product> getNewestProducts() {
        return productsRepository.findAllByOrderByUploadDateDesc();
    }

    @Override
    public List<Product> getProductsByOwner(String ownerName) {
        return null;
    }

//    @Override
//    public List<Product> getFeaturedProducts() {
//        return null;
//    }

    @Override
    public List<Product> getMostPopularProducts() {
        return productsRepository.findAllByOrderByNumberOfDownloadsDesc();
    }
}
