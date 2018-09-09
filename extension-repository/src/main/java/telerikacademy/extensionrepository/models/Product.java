package telerikacademy.extensionrepository.models;

import telerikacademy.extensionrepository.anotations.Unique;
import telerikacademy.extensionrepository.constants.Constants;
import telerikacademy.extensionrepository.services.ProductServiceImpl;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, message = "Product name should be at least 2 characters long.")
    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String version;

    private Date uploadDate;

    @ManyToOne
    private User owner;

    @Column(columnDefinition = "INT DEFAULT 0")
    private long numberOfDownloads;

    @Column(nullable = false, unique = true)
    private String downloadLink;

    @NotNull
    @Pattern(regexp = Constants.GITHUB_PATTERN, message = "Invalid source repository link!")
    @Column(nullable = false, unique = true)
    private String sourceRepositoryLink;

    private long openIssues;

    private long pullRequests;

    private Date lastCommitDate;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT 'PENDING'", nullable = false)
    private String productStatus;

    @ManyToMany
    private List<Tag> tags;

    @OneToOne(cascade = CascadeType.ALL)
    private File file;

    @OneToOne(cascade = CascadeType.ALL)
    private File productPicture;

    private boolean featuredProduct;

    public Product() {
        this.tags= new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public long getNumberOfDownloads() {
        return numberOfDownloads;
    }

    public void setNumberOfDownloads(long numberOfDownloads) {
        this.numberOfDownloads = numberOfDownloads;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public String getSourceRepositoryLink() {
        return sourceRepositoryLink;
    }

    public void setSourceRepositoryLink(String sourceRepositoryLink) {
        this.sourceRepositoryLink = sourceRepositoryLink;
    }

    public long getOpenIssues() {
        return openIssues;
    }

    public void setOpenIssues(long openIssues) {
        this.openIssues = openIssues;
    }

    public long getPullRequests() {
        return pullRequests;
    }

    public void setPullRequests(long pullRequests) {
        this.pullRequests = pullRequests;
    }

    public Date getLastCommitDate() {
        return lastCommitDate;
    }

    public void setLastCommitDate(Date lastCommitDate) {
        this.lastCommitDate = lastCommitDate;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public File getProductPicture() {
        return productPicture;
    }

    public void setProductPicture(File productPicture) {
        this.productPicture = productPicture;
    }

    public boolean isFeaturedProduct() {
        return featuredProduct;
    }

    public void setFeaturedProduct(boolean featuredProduct) {
        this.featuredProduct = featuredProduct;
    }
}