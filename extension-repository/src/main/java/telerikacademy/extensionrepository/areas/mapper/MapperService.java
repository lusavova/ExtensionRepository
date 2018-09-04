package telerikacademy.extensionrepository.areas.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telerikacademy.extensionrepository.areas.files.enums.StorageType;
import telerikacademy.extensionrepository.areas.files.models.File;
import telerikacademy.extensionrepository.areas.files.services.base.StorageService;
import telerikacademy.extensionrepository.areas.github.services.base.GithubService;
import telerikacademy.extensionrepository.areas.products.data.ProductsRepository;
import telerikacademy.extensionrepository.areas.products.models.Product;
import telerikacademy.extensionrepository.areas.products.models.dto.ProductDTO;
import telerikacademy.extensionrepository.areas.users.models.User;
import telerikacademy.extensionrepository.areas.users.models.UserDTO;
import telerikacademy.extensionrepository.areas.users.services.base.UserService;
import telerikacademy.extensionrepository.exceptions.FormatExeption;

import java.io.IOException;

@Service
public class MapperService {
    private GithubService githubService;
    private UserService userService;
    private StorageService storageService;

    @Autowired
    public MapperService(GithubService githubService,
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

        product.setSourceRepositoryLink(productDTO.getSourceRepositoryLink());

        try {
            githubService.getGithubInfo(product);
        } catch (IOException e) {
            e.printStackTrace();
        }

        product.setTags(productDTO.getTags());

        File file = storageService.getById(productDTO.getFileId());
        if (!file.getType().equals(StorageType.FILE.name())) {
            throw new FormatExeption("Not a file.");
        }
        product.setFile(file);
        product.setDownloadLink(file.getDownloadLink());

        File productImage = storageService.getById(productDTO.getProductPictureId());
        if (productImage == null) {
            // user default picture
        } else {
            if (!productImage.getType().equals(StorageType.IMAGE.name())) {
                throw new FormatExeption("Not an image.");
            }
            product.setProductPicture(productImage);
        }

        return product;
    }

    public User mapUserDTOToUser(UserDTO userDTO) {
        User user = new User();
        return user;
    }
}
