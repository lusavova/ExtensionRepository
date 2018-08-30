package telerikacademy.extensionrepository.areas.products.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telerikacademy.extensionrepository.areas.products.data.ProductsRepository;
import telerikacademy.extensionrepository.areas.products.models.Product;
import telerikacademy.extensionrepository.areas.products.services.base.SortProductsService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SortProductsServiceImpl implements SortProductsService {
    private ProductsRepository productsRepository;

    @Autowired
    public SortProductsServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
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
        return productsRepository.findAllByOrderByUploadDateDesc();
    }

    @Override
    public List<Product> findAllSortedByLastCommitDateDesc() {
        return productsRepository.findAllByOrderByLastCommitDateDesc();
    }

    @Override
    public List<Product> findTop10SortedByNumberOfDownloadsDesc() {
        return productsRepository.findTop10ByOrderByNumberOfDownloadsDesc();
    }

    @Override
    public List<Product> findTop10SortedByUploadDateDesc() {
        return productsRepository.findTop10ByOrderByUploadDateDesc();
    }
}
