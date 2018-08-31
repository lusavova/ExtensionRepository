package telerikacademy.extensionrepository.areas.github.services;

import com.sun.media.sound.InvalidFormatException;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.springframework.stereotype.Service;
import telerikacademy.extensionrepository.areas.products.models.Product;
import telerikacademy.extensionrepository.areas.github.services.base.GithubService;
import telerikacademy.extensionrepository.exceptions.FormatExeption;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class GithubServiceImpl implements GithubService {
    private static final String GITHUB_PATTERN = "(?<remove>https:\\/\\/github\\.com\\/)(?<name>.*)";

    public void saveGithubProductInfo(Product product) {
        GitHub gitHub = connectToGitHub();
        String repo = checkRepositoryLink(product.getSourceRepositoryLink());
        GHRepository repository = null;
        try {
            repository = gitHub.getRepository(repo);
            product.setPullRequests(repository.getPullRequests(GHIssueState.OPEN).size());
            product.setOpenIssues(repository.getIssues(GHIssueState.OPEN).size());
            Date lastCommitDate = Objects.requireNonNull(repository.listCommits()
                    .asList()
                    .stream()
                    .findFirst()
                    .orElse(null))
                    .getCommitDate();
            product.setLastCommitDate(lastCommitDate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private GitHub connectToGitHub(){
        GitHub gitHub = null;
        try {
            gitHub = GitHub.connect("ivanstoykovivanov-telerik", " 3f118f826b409066ada2b9daa4b45a00354a56c2");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gitHub;
    }

    private String checkRepositoryLink(String repoLink){
        Pattern pattern = Pattern.compile(GITHUB_PATTERN);
        Matcher matcher = pattern.matcher(repoLink);
        String repo = null;
        if (matcher.find()){
            repo = matcher.group("name");
        } else {
            throw new FormatExeption("Invalid repository link.");
        }
        return repo;
    }
}
