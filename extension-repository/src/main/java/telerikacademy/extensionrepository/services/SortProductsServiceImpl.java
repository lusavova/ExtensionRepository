package telerikacademy.extensionrepository.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telerikacademy.extensionrepository.data.ProductsRepository;
import telerikacademy.extensionrepository.models.Product;
import telerikacademy.extensionrepository.services.base.SortProductsService;

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
    public List<Product> findTopNSortedByNumberOfDownloadsDesc(int number) {
        return (List<Product>) productsRepository.findAllByOrderByNumberOfDownloadsDesc().stream().limit(number);
    }

    @Override
    public List<Product> findTopNSortedByUploadDateDesc(int number) {
        return (List<Product>) productsRepository.findAllByOrderByUploadDateDesc().stream().limit(number);
    }
}
