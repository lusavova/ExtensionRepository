package telerikacademy.extensionrepository.areas.github;

import java.util.Date;

public class GitHubDTO {
    private long pullRequest;
    private long openIssues;
    private Date lastCommitDate;

    public long getPullRequest() {
        return pullRequest;
    }

    public void setPullRequest(long pullRequest) {
        this.pullRequest = pullRequest;
    }

    public long getOpenIssues() {
        return openIssues;
    }

    public void setOpenIssues(long openIssues) {
        this.openIssues = openIssues;
    }

    public Date getLastCommitDate() {
        return lastCommitDate;
    }

    public void setLastCommitDate(Date lastCommitDate) {
        this.lastCommitDate = lastCommitDate;
    }
}
