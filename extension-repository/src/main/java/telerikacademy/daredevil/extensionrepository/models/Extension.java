package telerikacademy.daredevil.extensionrepository.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "extensions")
public class Extension {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "extension_name", nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String version;

    @Column(nullable = false)
    private User owner;

    @Column(name = "number_of_downloads", nullable = false)
    private int numberOfDownloads;

    @Column(name = "download_link", nullable = false)
    private String downloadLink;

    @Column(name = "source_repository_link", nullable = false)
    private String sourceRepositoryLink;

    @Column(name = "number_of_open_issues", nullable = false)
    private int numberOfOpenIssues;

    @Column(name = "number_of_pull_requests", nullable = false)
    private int numberOfPullRequests;

    @Column(name = "last_commit_date", nullable = false)
    private Date lastCommitDate;

    @OneToMany
    private List<Tag> tags;

    public Extension() {
    }

//    public Extension(String name, String description, String version, User owner, int numberOfDownloads, String downloadLink, String sourceRepositoryLink, int numberOfOpenIssues, int numberOfPullRequests, Date lastCommitDate, List<Tag> tags) {
//        setName(name);
//        setDescription(description);
//        setVersion(version);
//        setOwner(owner);
//        setNumberOfDownloads(numberOfDownloads);
//        setDownloadLink(downloadLink);
//        setSourceRepositoryLink(sourceRepositoryLink);
//        setNumberOfOpenIssues(numberOfOpenIssues);
//        setNumberOfPullRequests(numberOfPullRequests);
//        setLastCommitDate(lastCommitDate);
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public int getNumberOfDownloads() {
        return numberOfDownloads;
    }

    public void setNumberOfDownloads(int numberOfDownloads) {
        this.numberOfDownloads = numberOfDownloads;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
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

    public int getNumberOfOpenIssues() {
        return numberOfOpenIssues;
    }

    public void setNumberOfOpenIssues(int numberOfOpenIssues) {
        this.numberOfOpenIssues = numberOfOpenIssues;
    }

    public int getNumberOfPullRequests() {
        return numberOfPullRequests;
    }

    public void setNumberOfPullRequests(int numberOfPullRequests) {
        this.numberOfPullRequests = numberOfPullRequests;
    }

    public Date getLastCommitDate() {
        return lastCommitDate;
    }

    public void setLastCommitDate(Date lastCommitDate) {
        this.lastCommitDate = lastCommitDate;
    }
}
