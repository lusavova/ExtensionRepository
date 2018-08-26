package telerikacademy.extensionrepository.services.base;

import telerikacademy.extensionrepository.models.Product;

import java.io.IOException;

public interface GithubService {
    void saveGihtubInfo(Product product) throws IOException;
}
