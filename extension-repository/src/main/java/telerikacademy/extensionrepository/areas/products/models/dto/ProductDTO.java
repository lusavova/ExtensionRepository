package telerikacademy.extensionrepository.areas.products.models.dto;

import telerikacademy.extensionrepository.areas.tags.models.Tag;

import java.util.Date;
import java.util.List;

public class ProductDTO {
    private String name;
    private String description;
    private String version;
    private long ownerId;
    private String sourceRepositoryLink;
    private List<Tag> tags;
    private long fileId;
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

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
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
}