package telerikacademy.extensionrepository.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import telerikacademy.extensionrepository.models.File;
import telerikacademy.extensionrepository.mapper.ProductDTOMapper;
import telerikacademy.extensionrepository.data.ProductsRepository;
import telerikacademy.extensionrepository.enums.ProductStatus;
import telerikacademy.extensionrepository.exceptions.ProductNotFoundExeption;
import telerikacademy.extensionrepository.models.dto.ProductDTO;
import telerikacademy.extensionrepository.services.base.ProductService;
import telerikacademy.extensionrepository.models.Product;
import telerikacademy.extensionrepository.services.base.StorageService;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductsRepository productsRepository;
    private ProductDTOMapper mapper;
    private StorageService storageService;

    @Autowired
    public ProductServiceImpl(ProductsRepository productsRepository,
                                  ProductDTOMapper mapper,
                              StorageService storageService) {
        this.productsRepository = productsRepository;
        this.mapper = mapper;
        this.storageService = storageService;
    }

    @Override
    public Product findById(long id) {
        return productsRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundExeption("Cannot find product with id = " + id));
    }

    @Override
    public Product addProduct(ProductDTO productDTO) {
        Product product = mapper.mapProductDTOToProduct(productDTO);
        product.setUploadDate(new Date());
        product.setProductStatus(ProductStatus.PENDING.name());
        product.setNumberOfDownloads(0);
        return save(product);
    }

    @Override
    public Product updateProduct(Product updateProduct, long id) {
        findById(id);
        return save(updateProduct);
    }

    @Override
    public void deleteProduct(long id) {
        Product product =  findById(id);
        productsRepository.deleteById(id);

        Path filePath = Paths.get(product.getFile().getDownloadLink());
        storageService.deleteFilesFromSystem(filePath);
        Path imagePath = Paths.get(product.getProductPicture().getDownloadLink());
        storageService.deleteFilesFromSystem(imagePath);
    }

    @Override
    public boolean fieldValueExists(Object value, String fieldName) {
        Assert.notNull(fieldName, String.format("%s already exist.", formatField(fieldName)));

        if (value == null) {
            return false;
        }

        return setOfValues(fieldName).contains(value);
    }

    @Override
    public List<Product> listAllProducts() {
        return productsRepository.findAll();
    }

    @Override
    public List<Product> listAllDisabledProducts() {
        return productsRepository.listAllDisabledProducts();
    }

    @Override
    public List<Product> listAllActiveProducts() {
        return productsRepository.listAllActiveProducts();
    }

    @Override
    public List<Product> listAllPendingProducts() {
        return productsRepository.listAllPendingProducts();
    }

    @Override
    public void changeProductStatus(long id, String status) {
        Product product = findById(id);
        product.setProductStatus(status);
        save(product);
    }

    @Override
    public void changeFeatureProductStatus(long id, boolean status) {
        Product product = findById(id);
        product.setFeaturedProduct(status);
        save(product);
    }

    @Override
    public boolean githubRepoAlreadyExists(String account) {
        Set<String> accounts = listAllProducts()
                .stream()
                .map(Product::getSourceRepositoryLink)
                .collect(Collectors.toSet());

        if (accounts.contains(account)) {
            return true;
        }
        return false;
    }

    @Override
    public void increaseNumOfDownload(long productId) {
        Product product = findById(productId);
        product.setNumberOfDownloads(product.getNumberOfDownloads() + 1);
        save(product);
    }

    private Product save(Product product) {
        return productsRepository.saveAndFlush(product);
    }

    private Set<Object> setOfValues(Object fieldName) {
        Set<Object> values;

        switch (fieldName.toString()) {
            case "name":
                values = productsRepository.findAll().stream()
                        .map(Product::getName)
                        .collect(Collectors.toSet());
                break;
            case "sourceRepositoryLink":
                values = productsRepository.findAll().stream()
                        .map(Product::getSourceRepositoryLink)
                        .collect(Collectors.toSet());
                break;
            case "fileId":
                values = productsRepository.findAll().stream()
                        .map(Product::getFile)
                        .map(File::getId)
                        .collect(Collectors.toSet());
                break;
            case "productPictureId":
                values = productsRepository.findAll().stream()
                        .map(Product::getProductPicture)
                        .map(File::getId)
                        .collect(Collectors.toSet());
                break;
            default:
                throw new IllegalArgumentException("No such product field.");
        }

        return values;
    }

    private String formatField(String fieldName) {
        char firstLetter = fieldName.toUpperCase().charAt(0);
        String resultLetters = fieldName.substring(1).toLowerCase();
        return firstLetter + resultLetters;
    }
}
