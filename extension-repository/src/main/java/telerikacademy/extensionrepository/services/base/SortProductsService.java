package telerikacademy.extensionrepository.services.base;

import telerikacademy.extensionrepository.models.Product;

import java.util.List;

public interface SortProductsService {

    List<Product> findAllSortedByName();

    List<Product> findAllFeaturedProducts();

    List<Product> findAllSortedByNumberOfDownloadsDesc();

    List<Product> findAllSortedByUploadDateDesc();

    List<Product> findAllSortedByLastCommitDateDesc();

    List<Product> findTopNSortedByNumberOfDownloadsDesc(int number);

    List<Product> findTopNSortedByUploadDateDesc(int number);

    List<Product> findTopNFeaturedProducts(int number);
}
