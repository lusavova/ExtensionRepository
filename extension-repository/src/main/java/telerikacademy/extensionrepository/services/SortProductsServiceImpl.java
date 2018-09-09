package telerikacademy.extensionrepository.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telerikacademy.extensionrepository.data.ProductsRepository;
import telerikacademy.extensionrepository.models.Product;
import telerikacademy.extensionrepository.services.base.SortProductsService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<Product> findAllFeaturedProducts() {
        return productsRepository.findAllFeaturedProducts();
    }

    @Override
    public List<Product> findAllSortedByNumberOfDownloadsDesc() {
        return productsRepository.findAllProductsSortedByNumberOfDownloadsDesc();
    }

    @Override
    public List<Product> findAllSortedByUploadDateDesc() {
        return productsRepository.findAllProductsSortedByUploadDateDesc();
    }

    @Override
    public List<Product> findAllSortedByLastCommitDateDesc() {
        return productsRepository.findAllProductsSortedByLastCommitDateDesc();
    }

    @Override
    public List<Product> findTopNSortedByNumberOfDownloadsDesc(int number) {
        return productsRepository
                .findAllProductsSortedByNumberOfDownloadsDesc()
                .stream()
                .limit(number)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findTopNSortedByUploadDateDesc(int number) {
        return productsRepository
                .findAllProductsSortedByUploadDateDesc()
                .stream()
                .limit(number)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findTopNFeaturedProducts(int number) {
        return findAllFeaturedProducts()
                .stream()
                .limit(number)
                .collect(Collectors.toList());
    }
}
