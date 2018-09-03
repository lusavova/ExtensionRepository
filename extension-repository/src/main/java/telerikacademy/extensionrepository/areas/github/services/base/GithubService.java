package telerikacademy.extensionrepository.areas.github.services.base;

import telerikacademy.extensionrepository.areas.products.models.Product;

import java.io.IOException;

public interface GithubService {
    void getGithubInfo(Product product) throws IOException;
}
