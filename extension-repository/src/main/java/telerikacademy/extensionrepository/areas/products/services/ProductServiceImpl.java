package telerikacademy.extensionrepository.areas.products.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telerikacademy.extensionrepository.areas.mapper.MapperService;
import telerikacademy.extensionrepository.areas.products.data.ProductsRepository;
import telerikacademy.extensionrepository.areas.products.exeptions.ProductNotFoundExeption;
import telerikacademy.extensionrepository.areas.products.services.base.ProductService;
import telerikacademy.extensionrepository.areas.products.models.dto.ProductDTO;
import telerikacademy.extensionrepository.areas.products.models.Product;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductsRepository productsRepository;
    private MapperService mapperService;

    @Autowired
    public ProductServiceImpl(ProductsRepository productsRepository,
                              MapperService mapperService) {
        this.productsRepository = productsRepository;
        this.mapperService = mapperService;
    }

    @Override
    public List<Product> listAllProducts() {
        return productsRepository.findAll();
    }

    @Override
    public Product findById(long id) {
        return productsRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundExeption("Cannot find product with id = " + id));
    }

    @Override
    public Product addProduct(ProductDTO productDTO) {
        //validateProduct(productDTO);
        Product product = mapperService.mapProductDTOToProduct(productDTO);
        product.setUploadDate(new Date());
        product.setProductState("pending");
        product.setNumberOfDownloads(0);
//        addGithubInfo(product);
        return productsRepository.saveAndFlush(product);
    }

    @Override
    public Product updateProduct(long id, Product updateProduct) {
//        validateProduct(updateProduct);
        return productsRepository.saveAndFlush(updateProduct);
    }

    @Override
    public void deleteProduct(long id) {
        productsRepository.deleteById(id);
    }
//
//    private void addGithubInfo(Product product) {
//        try {
//            githubService.saveGithubProductInfo(product);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    private void validateProduct(ProductDTO product) {
//        List<Product> products = productsRepository.findAll();
//        boolean isNamePresent = products.stream().anyMatch(p -> p.getName().equals(product.getName()));
//        boolean isRepositoryLinkPresent = products.stream()
//                .anyMatch(p -> p.getSourceRepositoryLink().equals(product.getSourceRepositoryLink()));
//
//        if (isNamePresent) {
//            //Exeption type???
//            throw new InvalidArgumentExeption("Product name already exist");
//        }
//        if (isRepositoryLinkPresent) {
//            throw new InvalidArgumentExeption("Source repository link already exist");
//        }
//    }
}
