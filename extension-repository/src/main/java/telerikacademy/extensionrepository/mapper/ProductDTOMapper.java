package telerikacademy.extensionrepository.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import telerikacademy.extensionrepository.enums.StorageType;
import telerikacademy.extensionrepository.models.File;
import telerikacademy.extensionrepository.services.base.StorageService;
import telerikacademy.extensionrepository.models.dto.GitHubDTO;
import telerikacademy.extensionrepository.services.base.GithubService;
import telerikacademy.extensionrepository.models.Product;
import telerikacademy.extensionrepository.models.dto.ProductDTO;
import telerikacademy.extensionrepository.models.Tag;
import telerikacademy.extensionrepository.models.dto.TagDTO;
import telerikacademy.extensionrepository.services.base.TagsService;
import telerikacademy.extensionrepository.models.User;
import telerikacademy.extensionrepository.services.base.UserService;
import telerikacademy.extensionrepository.exceptions.FormatExeption;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductDTOMapper {
    private GithubService githubService;
    private UserService userService;
    private StorageService storageService;
    private TagsService tagsService;

    @Autowired
    public ProductDTOMapper(GithubService githubService,
                            UserService userService,
                            StorageService storageService,
                            TagsService tagsService) {
        this.githubService = githubService;
        this.userService = userService;
        this.storageService = storageService;
        this.tagsService = tagsService;
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
        long fileOwnerId = file.getOwner().getId();
        if (fileOwnerId != user.getId()){
            throw new IllegalArgumentException("File owner id is not the same as product owner id.");
        }

        if (!file.getType().equals(StorageType.FILE.name())) {
            throw new FormatExeption("Not a file");
        }
        product.setFile(file);

        if (productDTO.getProductPictureId() != 0) {
            File productPicture = storageService.findById(productDTO.getProductPictureId());
            if (productPicture.getOwner().getId() != user.getId()){
                throw new IllegalArgumentException("Image owner id is not the same as product owner id.");
            }
            if (!productPicture.getType().equals(StorageType.IMAGE.name())) {
                throw new FormatExeption("Not an image");
            }
            product.setProductPicture(productPicture);
        }

        GitHubDTO gitHubDTO = githubService.getGithubInfo(repositoryLink);
        product.setOpenIssues(gitHubDTO.getOpenIssues());
        product.setPullRequests(gitHubDTO.getPullRequest());
        product.setLastCommitDate(gitHubDTO.getLastCommitDate());

        Set<String> allTagnames = tagsService.listAll()
                .stream()
                .map(Tag::getTagname)
                .collect(Collectors.toSet());

        List<Tag> productTags = new ArrayList<>();
        for (String tagname : productDTO.getTags()) {
            if (allTagnames.contains(tagname)) {
                continue;
            }
            TagDTO tagDTO = new TagDTO(tagname);
            productTags.add(tagsService.add(tagDTO));
        }
        product.setTags(productTags);

        product.setDownloadLink(file.getDownloadLink());
        return product;
    }
}
