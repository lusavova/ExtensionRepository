package telerikacademy.extensionrepository.services;

import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.springframework.stereotype.Service;
import telerikacademy.extensionrepository.models.Product;
import telerikacademy.extensionrepository.services.base.GithubService;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class GithubServiceImpl implements GithubService {
    public void saveGihtubInfo(Product product) throws IOException {
        GitHub gitHub = GitHub.connect("lusavova", " a23459f85d0700ea0ec696d685f06912d7b8a7ab");

        String repoLink = product.getSourceRepositoryLink();
        Pattern pattern = Pattern.compile("(?<remove>https:\\/\\/github\\.com\\/)(?<name>.*)");
        Matcher matcher = pattern.matcher(repoLink);
        String repo = null;
        if (matcher.find()){
            repo = matcher.group("name");
        }

        if (repo == null){
            throw new IllegalArgumentException("Repository name can't be null.");
        }

        GHRepository repository = gitHub.getRepository(repo);
        product.setPullRequests(repository.getPullRequests(GHIssueState.OPEN).size());
        product.setOpenIssues(repository.getIssues(GHIssueState.OPEN).size());
        Date lastCommitDate = Objects.requireNonNull(repository.listCommits().asList().stream().findFirst().orElse(null)).getCommitDate();
        product.setLastCommitDate(lastCommitDate);
    }
}