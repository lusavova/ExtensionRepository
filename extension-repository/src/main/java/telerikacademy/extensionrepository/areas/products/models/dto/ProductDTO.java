package telerikacademy.extensionrepository.areas.products.models.dto;

import org.springframework.format.annotation.NumberFormat;
import telerikacademy.extensionrepository.anotations.Unique;
import telerikacademy.extensionrepository.areas.products.services.ProductServiceImpl;
import telerikacademy.extensionrepository.areas.tags.models.Tag;
import telerikacademy.extensionrepository.constants.Constants;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

public class ProductDTO {
    @NotNull
    @Unique(service = ProductServiceImpl.class, fieldName = "name", message = "Name already exists.")
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String version;

    @NotNull
    @NumberFormat
    private long ownerId;

    @Pattern(regexp = Constants.GITHUB_PATTERN, message = "Invalid source repository link!")
    @Unique(service = ProductServiceImpl.class, fieldName = "sourceRepositoryLink", message = "Repository link already exists.")
    private String sourceRepositoryLink;

    //TO DO :STRING
    private List<String> tags;

    @NotNull
    @NumberFormat
    @Unique(service = ProductServiceImpl.class, fieldName = "fileId", message = "File already exists.")
    private long fileId;

    @NumberFormat
    @Unique(service = ProductServiceImpl.class, fieldName = "productPictureId", message = "Picture already exists.")
    private long productPictureId;

//    private List<Long> productPictures;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public String getSourceRepositoryLink() {
        return sourceRepositoryLink;
    }

    public void setSourceRepositoryLink(String sourceRepositoryLink) {
        this.sourceRepositoryLink = sourceRepositoryLink;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public long getProductPictureId() {
        return productPictureId;
    }

    public void setProductPictureId(long productPictureId) {
        this.productPictureId = productPictureId;
    }

//    public List<Long> getProductPictures() {
//        return productPictures;
//    }
//
//    public void setProductPictures(List<Long> productPictures) {
//        this.productPictures = productPictures;
//    }


    @Override
    public String toString() {
        return "ProductDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", version='" + version + '\'' +
                ", ownerId=" + ownerId +
                ", sourceRepositoryLink='" + sourceRepositoryLink + '\'' +
                ", tags=" + tags +
                ", fileId=" + fileId +
                ", productPictureId=" + productPictureId +
                '}';
    }
}