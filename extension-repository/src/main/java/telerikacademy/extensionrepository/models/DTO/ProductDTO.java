package telerikacademy.extensionrepository.models.DTO;

import telerikacademy.extensionrepository.models.File;
import telerikacademy.extensionrepository.models.Tag;

import java.util.Date;
import java.util.List;

public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private String version;
    private Date uploadDate;
    private String ownerId;
    private long numberOfDownloads;
    private String downloadLink;
    private String sourceRepositoryLink;
    private long openIssues;
    private long pullRequests;
    private Date lastCommitDate;
    private String productState;
    private List<Tag> tags;
    private File file;



}
