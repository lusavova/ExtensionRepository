package telerikacademy.extensionrepository.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import telerikacademy.extensionrepository.models.Product;
import telerikacademy.extensionrepository.services.base.SearchService;

import java.util.List;

@RestController
public class SearchController {

    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public @ResponseBody
    List<Product> searchProducts(@RequestParam String term) {
        return searchService.getAllProductsByParam(term);
    }
}
