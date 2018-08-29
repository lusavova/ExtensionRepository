package telerikacademy.extensionrepository.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telerikacademy.extensionrepository.data.ProductsRepository;
import telerikacademy.extensionrepository.models.DTO.ProductDTO;
import telerikacademy.extensionrepository.models.File;
import telerikacademy.extensionrepository.models.Product;
import telerikacademy.extensionrepository.models.User;
import telerikacademy.extensionrepository.services.base.GithubService;
import telerikacademy.extensionrepository.services.base.ProductService;
import telerikacademy.extensionrepository.services.base.FileStorageService;
import telerikacademy.extensionrepository.services.base.UserService;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductsRepository productsRepository;
    private GithubService githubService;
    private UserService userService;
    private FileStorageService fileStorageService;

    @Autowired
    public ProductServiceImpl(ProductsRepository productsRepository,
                              GithubService githubService,
                              UserService userService, FileStorageService fileStorageService) {
        this.productsRepository = productsRepository;
        this.githubService = githubService;
        this.userService = userService;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public List<Product> listAllProducts() {
        return productsRepository.findAll();
    }

    @Override
    public Product findById(long id) {
        return productsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Cannot find product with id = " + id));
    }

    @Override
    public Product addProduct(ProductDTO productDTO) {
        validateProduct(productDTO);

        Product product = bindProductDTOtoProduct(productDTO);

        Date uploadDate = new Date();
        product.setUploadDate(uploadDate);
        product.setProductState("pending");
        addGithubInfo(product);

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

    private void addGithubInfo(Product product) {
        try {
            githubService.saveGithubProductInfo(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validateProduct(ProductDTO product){
        List<Product> products = productsRepository.findAll();
        boolean isNamePresent = products.stream().anyMatch(p -> p.getName().equals(product.getName()));
        boolean isRepoLinkPresent = products.stream()
                .anyMatch(p -> p.getSourceRepositoryLink().equals(product.getSourceRepositoryLink()));

        if (isNamePresent) {
            throw new IllegalArgumentException("Product name already exist");
        }
        if (isRepoLinkPresent) {
            throw  new IllegalArgumentException("Source repository link already exist");
        }
    }

    private Product bindProductDTOtoProduct(ProductDTO productDTO){
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setVersion(productDTO.getVersion());

        User user = userService.getUserById(productDTO.getOwnerId());
        product.setOwner(user);

        product.setNumberOfDownloads(productDTO.getNumberOfDownloads());
        product.setDownloadLink(productDTO.getDownloadLink());
        product.setSourceRepositoryLink(productDTO.getSourceRepositoryLink());

        File file = fileStorageService.getById(productDTO.getFileId());
        product.setFile(file);

        product.setTags(productDTO.getTags());

        return product;
    }
}
