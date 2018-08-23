package telerikacademy.extensionrepository.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telerikacademy.extensionrepository.data.ProductsRepository;
import telerikacademy.extensionrepository.models.Product;
import telerikacademy.extensionrepository.services.base.ProductService;
import telerikacademy.extensionrepository.services.base.SearchService;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    private ProductsRepository productsRepository;

    @Autowired
    public SearchServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public List<Product> getAllProductsByParam(String param) {
        return productsRepository.findAllByNameContaining(param);
    }
}
