package telerikacademy.extensionrepository.areas.github.services;

import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.springframework.stereotype.Service;
import telerikacademy.extensionrepository.areas.github.services.base.GithubService;
import telerikacademy.extensionrepository.areas.products.models.Product;
import telerikacademy.extensionrepository.exceptions.FormatExeption;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class GithubServiceImpl implements GithubService {
    private static final String GITHUB_PATTERN = "(?<remove>https:\\/\\/github\\.com\\/)(?<name>.*)";

    @Override
    public void getGithubInfo(Product product) {
        GitHub gitHub = connectToGitHub();
        String repo = checkRepositoryLink(product.getSourceRepositoryLink());
        GHRepository repository;
        try {
            repository = gitHub.getRepository(repo);
            long pullRequest = repository.getPullRequests(GHIssueState.OPEN).size();
            long openIssues = repository.getIssues(GHIssueState.OPEN).size();
            Date lastCommitDate = Objects.requireNonNull(repository.listCommits()
                    .asList()
                    .stream()
                    .findFirst()
                    .orElse(null))
                    .getCommitDate();

            product.setPullRequests(pullRequest);
            product.setOpenIssues(openIssues);
            product.setLastCommitDate(lastCommitDate);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private GitHub connectToGitHub(){
        GitHub gitHub = null;
        try {
            gitHub = GitHub.connect("lusavova", " ad03b2f1cb2d5125f8aeabb4cc3b73a99f2e1cdc");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gitHub;
    }

    private String checkRepositoryLink(String repoLink){
        Pattern pattern = Pattern.compile(GITHUB_PATTERN);
        Matcher matcher = pattern.matcher(repoLink);
        String repo;
        if (matcher.find()){
            repo = matcher.group("name");
        } else {
            throw new FormatExeption("Invalid repository link.");
        }
        return repo;
    }
}
