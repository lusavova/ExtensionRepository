package telerikacademy.extensionrepository.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telerikacademy.extensionrepository.areas.products.controllers.ProductsController;
import telerikacademy.extensionrepository.areas.products.models.Product;
import telerikacademy.extensionrepository.areas.products.services.base.ProductService;
import telerikacademy.extensionrepository.areas.tags.models.Tag;

import java.sql.Struct;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class SearchController {
    private ProductService productService;

    @Autowired
    public SearchController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/search/{text}")
    @ResponseBody
    public List<Product> products(@PathVariable String text) {
        String searchText = text.toLowerCase();

        List<Product> products = productService
                .listAllProducts()
                .stream()
                .filter(pr -> {

                    String desc = pr.getDescription().toLowerCase();
                    String name = pr.getName().toLowerCase();

                    Set<String> tags = pr.getTags().stream()
                            .map(Tag::getTagname)
                            .map(String::toLowerCase)
                            .collect(Collectors.toSet());

                    return name.contains(searchText) || desc.contains(searchText) || tags.contains(searchText);
                })
                .collect(Collectors.toList());

        return products;
    }
}
