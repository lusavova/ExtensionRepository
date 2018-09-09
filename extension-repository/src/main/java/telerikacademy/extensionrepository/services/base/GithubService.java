package telerikacademy.extensionrepository.services.base;

import telerikacademy.extensionrepository.models.dto.GitHubDTO;

public interface GithubService {
    GitHubDTO getGithubInfo(String repositoryLink);
}
