//package telerikacademy.daredevil.extensionrepository.models;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Entity
//@Table(name = "github_info")
//public class GithubInfo {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false)
//    private String repositoryLink;
//
//    private int numberOfOpenIssues;
//
//    private int numberOfPullRequests;
//
//    private Date lastCommitDate;
//
//    @OneToOne(mappedBy = "githubInfo")
//    private Product product;
//
//    public GithubInfo() {
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getRepositoryLink() {
//        return repositoryLink;
//    }
//
//    public void setRepositoryLink(String repositoryLink) {
//        this.repositoryLink = repositoryLink;
//    }
//
//    public int getNumberOfOpenIssues() {
//        return numberOfOpenIssues;
//    }
//
//    public void setNumberOfOpenIssues(int numberOfOpenIssues) {
//        this.numberOfOpenIssues = numberOfOpenIssues;
//    }
//
//    public int getNumberOfPullRequests() {
//        return numberOfPullRequests;
//    }
//
//    public void setNumberOfPullRequests(int numberOfPullRequests) {
//        this.numberOfPullRequests = numberOfPullRequests;
//    }
//
//    public Date getLastCommitDate() {
//        return lastCommitDate;
//    }
//
//    public void setLastCommitDate(Date lastCommitDate) {
//        this.lastCommitDate = lastCommitDate;
//    }
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
//}
