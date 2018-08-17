package telerikacademy.daredevil.extensionrepository.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "github_info")
public class GithubInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    @Column(name = "repository_link")
    private String repositoryLink;

    @Column(name = "number_of_open_issues")
    private int numberOfOpenIssues;

    @Column(name = "number_of_pull_requests")
    private int numberOfPullRequests;

    @Column(name = "last_commit_date")
    private Date lastCommitDate;

    public GithubInfo() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRepositoryLink() {
        return repositoryLink;
    }

    public void setRepositoryLink(String repositoryLink) {
        this.repositoryLink = repositoryLink;
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
