package telerikacademy.extensionrepository.areas.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import telerikacademy.extensionrepository.areas.files.models.File;
import telerikacademy.extensionrepository.areas.files.services.base.StorageService;
import telerikacademy.extensionrepository.areas.github.GitHubDTO;
import telerikacademy.extensionrepository.areas.github.services.base.GithubService;
import telerikacademy.extensionrepository.areas.products.models.Product;
import telerikacademy.extensionrepository.areas.products.models.dto.ProductDTO;
import telerikacademy.extensionrepository.areas.users.models.User;
import telerikacademy.extensionrepository.areas.users.services.base.UserService;

@Component
public class ProductDTOMapper {
    private GithubService githubService;
    private UserService userService;
    private StorageService storageService;

    @Autowired
    public ProductDTOMapper(GithubService githubService,
                            UserService userService,
                            StorageService storageService) {
        this.githubService = githubService;
        this.userService = userService;
        this.storageService = storageService;
    }

    public Product mapProductDTOToProduct(ProductDTO productDTO) {
        Product product = new Product();

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setVersion(productDTO.getVersion());

        User user = userService.findById(productDTO.getOwnerId());
        product.setOwner(user);

        String repositoryLink = productDTO.getSourceRepositoryLink();
        product.setSourceRepositoryLink(repositoryLink);

        File file = storageService.findById(productDTO.getFileId());
        product.setFile(file);

        if (productDTO.getProductPictureId() != 0) {
            File productPicture = storageService.findById(productDTO.getProductPictureId());
            product.setProductPicture(productPicture);
        }

        GitHubDTO gitHubDTO = githubService.getGithubInfo(repositoryLink);
        product.setOpenIssues(gitHubDTO.getOpenIssues());
        product.setPullRequests(gitHubDTO.getPullRequest());
        product.setLastCommitDate(gitHubDTO.getLastCommitDate());

        product.setTags(productDTO.getTags());

        product.setDownloadLink(file.getDownloadLink());
        return product;
    }
}
