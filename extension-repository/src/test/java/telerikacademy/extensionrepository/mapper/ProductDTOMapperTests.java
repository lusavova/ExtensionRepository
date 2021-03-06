package telerikacademy.extensionrepository.mapper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import telerikacademy.extensionrepository.enums.StorageType;
import telerikacademy.extensionrepository.models.File;
import telerikacademy.extensionrepository.services.base.StorageService;
import telerikacademy.extensionrepository.models.dto.GitHubDTO;
import telerikacademy.extensionrepository.services.base.GithubService;
import telerikacademy.extensionrepository.models.Product;
import telerikacademy.extensionrepository.models.dto.ProductDTO;
import telerikacademy.extensionrepository.models.Tag;
import telerikacademy.extensionrepository.services.base.TagsService;
import telerikacademy.extensionrepository.models.User;
import telerikacademy.extensionrepository.services.base.UserService;

import java.util.ArrayList;
import java.util.List;

public class ProductDTOMapperTests {
    private ProductDTOMapper productMapper;
    private GithubService mockGithubService = Mockito.mock(GithubService.class);
    private UserService mockUserService = Mockito.mock(UserService.class);
    private StorageService mockStorageService = Mockito.mock(StorageService.class);
    private TagsService mockTagsService = Mockito.mock(TagsService.class);

    @Before
    public void setUp() {
        productMapper = new ProductDTOMapper(mockGithubService, mockUserService, mockStorageService, mockTagsService);
    }

    @Test
    public void mapProductDTOToProduct_shouldMapAndReturnProduct() {
        String productName = "productName";
        String description = "description";
        String version = "version 1";
        Long ownerId = 1L;
        String sourceRepositoryLink = "repo link";
        String[] tags = new String[0];
        long fileId = 1;
        long productPictureId = 2;

        ProductDTO productDTO = new ProductDTO(
                productName, description, version, ownerId, sourceRepositoryLink, tags, fileId, productPictureId
        );

        Product expectedProduct = new Product();
        expectedProduct.setName(productDTO.getName());
        expectedProduct.setDescription(productDTO.getDescription());
        expectedProduct.setVersion(productDTO.getVersion());

        User user = new User();
        user.setId(ownerId);
        Mockito.when(mockUserService.findById(ownerId)).thenReturn(user);
        expectedProduct.setOwner(user);

        String repositoryLink = productDTO.getSourceRepositoryLink();
        expectedProduct.setSourceRepositoryLink(repositoryLink);

        File file = new File();
        file.setOwner(user);
        file.setType(StorageType.FILE.name());
        Mockito.when(mockStorageService.findById(fileId)).thenReturn(file);
        expectedProduct.setFile(file);

        File productPicture = new File();
        productPicture.setOwner(user);
        productPicture.setType(StorageType.IMAGE.name());
        Mockito.when(mockStorageService.findById(productPictureId)).thenReturn(productPicture);
        expectedProduct.setProductPicture(productPicture);

        GitHubDTO gitHubDTO = new GitHubDTO();
        Mockito.when(mockGithubService.getGithubInfo(repositoryLink)).thenReturn(gitHubDTO);
        expectedProduct.setOpenIssues(gitHubDTO.getOpenIssues());
        expectedProduct.setPullRequests(gitHubDTO.getPullRequest());
        expectedProduct.setLastCommitDate(gitHubDTO.getLastCommitDate());

        Mockito.when(mockTagsService.listAll()).thenReturn(new ArrayList<Tag>());

        List<Tag> productTags = new ArrayList<>();

        expectedProduct.setTags(productTags);
        expectedProduct.setDownloadLink(file.getDownloadLink());

        Product actualProduct = productMapper.mapProductDTOToProduct(productDTO);

        Assert.assertEquals(expectedProduct.getName(), actualProduct.getName());
        Assert.assertEquals(expectedProduct.getDescription(), actualProduct.getDescription());
        Assert.assertEquals(expectedProduct.getVersion(), actualProduct.getVersion());
        Assert.assertEquals(expectedProduct.getOwner().getId(), actualProduct.getOwner().getId());
        Assert.assertEquals(expectedProduct.getSourceRepositoryLink(), actualProduct.getSourceRepositoryLink());
        Assert.assertArrayEquals(expectedProduct.getTags().toArray(), actualProduct.getTags().toArray());
        Assert.assertEquals(expectedProduct.getFile().getId(), actualProduct.getFile().getId());
        Assert.assertEquals(expectedProduct.getProductPicture().getId(), actualProduct.getProductPicture().getId());
    }
}
