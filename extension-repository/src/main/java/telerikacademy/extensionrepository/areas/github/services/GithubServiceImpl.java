package telerikacademy.extensionrepository.areas.github.services;

import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.springframework.stereotype.Service;
import telerikacademy.extensionrepository.areas.github.GitHubDTO;
import telerikacademy.extensionrepository.areas.github.services.base.GithubService;
import telerikacademy.extensionrepository.areas.products.models.Product;
import telerikacademy.extensionrepository.constants.Constants;
import telerikacademy.extensionrepository.exceptions.FormatExeption;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class GithubServiceImpl implements GithubService {
    @Override
    public GitHubDTO getGithubInfo(String repositoryLink) {
        GitHubDTO gitHubDTO = new GitHubDTO();
        GitHub gitHub = connectToGitHub();
        GHRepository repository;
        try {
            repository = gitHub.getRepository(repositoryLink);

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

    private GitHub connectToGitHub(){
        GitHub gitHub = null;
        try {
            gitHub = GitHub.connectUsingOAuth("574926a0d8bcc1224bf0d35aaa3973c00ca2939d");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gitHub;
    }
}
