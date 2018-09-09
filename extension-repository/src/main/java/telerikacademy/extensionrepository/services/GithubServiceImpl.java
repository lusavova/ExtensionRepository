package telerikacademy.extensionrepository.services;

import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.springframework.stereotype.Service;
import telerikacademy.extensionrepository.models.dto.GitHubDTO;
import telerikacademy.extensionrepository.services.base.GithubService;
import telerikacademy.extensionrepository.constants.Constants;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

@Service
public class GithubServiceImpl implements GithubService {
    @Override
    public GitHubDTO getGithubInfo(String repositoryLink) {
        GitHubDTO gitHubDTO = new GitHubDTO();
        GitHub gitHub = connectToGitHub();
        GHRepository repository;
        try {
            repository = gitHub.getRepository(repositoryLink.replaceAll("https://github.com/", ""));

            long pullRequest = repository.getPullRequests(GHIssueState.OPEN).size();
            long openIssues = repository.getIssues(GHIssueState.OPEN).size();
            Date lastCommitDate = Objects.requireNonNull(repository.listCommits()
                    .asList()
                    .stream()
                    .findFirst()
                    .orElse(null))
                    .getCommitDate();

            gitHubDTO.setPullRequest(pullRequest);
            gitHubDTO.setOpenIssues(openIssues);
            gitHubDTO.setLastCommitDate(lastCommitDate);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return gitHubDTO;
    }

    private GitHub connectToGitHub() {
        GitHub gitHub = null;
        try {
            gitHub = GitHub.connect(Constants.GITHUB_USERNAME, Constants.OAUTH_ACCESS_TOKEN);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gitHub;
    }
}
