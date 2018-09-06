package telerikacademy.extensionrepository.areas.github.services.base;

import telerikacademy.extensionrepository.areas.github.GitHubDTO;

public interface GithubService {
    GitHubDTO getGithubInfo(String repositoryLink);
}
