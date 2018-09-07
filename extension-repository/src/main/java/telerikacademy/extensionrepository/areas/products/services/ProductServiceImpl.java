package telerikacademy.extensionrepository.areas.products.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import telerikacademy.extensionrepository.areas.files.models.File;
import telerikacademy.extensionrepository.areas.mapper.ProductDTOMapper;
import telerikacademy.extensionrepository.areas.products.data.ProductsRepository;
import telerikacademy.extensionrepository.areas.products.exeptions.ProductNotFoundExeption;
import telerikacademy.extensionrepository.areas.products.services.base.ProductService;
import telerikacademy.extensionrepository.areas.products.models.dto.ProductDTO;
import telerikacademy.extensionrepository.areas.products.models.Product;
import telerikacademy.extensionrepository.areas.users.models.User;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductsRepository productsRepository;
    private ProductDTOMapper mapper;

    @Autowired
    public ProductServiceImpl(ProductsRepository productsRepository,
                              ProductDTOMapper mapper) {
        this.productsRepository = productsRepository;
        this.mapper = mapper;
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
        Product product = mapper.mapProductDTOToProduct(productDTO);
        product.setUploadDate(new Date());
        product.setProductStatus("pending");
        product.setNumberOfDownloads(0);
        return productsRepository.saveAndFlush(product);
    }

    @Override
    public Product updateProduct(ProductDTO updateProduct) {
        Product product = mapper.mapProductDTOToProduct(updateProduct);
        return productsRepository.saveAndFlush(product);
    }

    @Override
    public void deleteProduct(long id) {
        productsRepository.deleteById(id);
    }

    @Override
    public boolean fieldValueExists(Object value, String fieldName) {
        Assert.notNull(fieldName, String.format("%s already exist.", formatField(fieldName)));

        if (value == null) {
            return false;
        }

        return setOfValues(fieldName).contains(value);
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
