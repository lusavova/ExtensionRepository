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
    public Product updateProduct(long id, Product updateProduct) {
        return productsRepository.saveAndFlush(updateProduct);
    }

    @Override
    public void deleteProduct(long id) {
        productsRepository.deleteById(id);
    }

}
