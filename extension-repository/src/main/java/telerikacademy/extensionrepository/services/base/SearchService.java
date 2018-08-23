package telerikacademy.extensionrepository.services.base;

import telerikacademy.extensionrepository.models.Product;

import java.util.List;

public interface SearchService {

    public List<Product> getAllProductsByParam(String param);
}
