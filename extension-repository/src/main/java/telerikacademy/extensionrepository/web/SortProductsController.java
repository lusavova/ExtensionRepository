package telerikacademy.extensionrepository.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telerikacademy.extensionrepository.models.Product;
import telerikacademy.extensionrepository.services.base.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class SortProductsController {

    private ProductService productService;

    @Autowired
    public SortProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public @ResponseBody
    List<Product> sortProductsByParam(@RequestParam String sortBy) {
        switch (sortBy) {
            case "Name":
                return productService.findAllSortedByName();
            case "Downloads":
                return productService.findAllSortedByNumberOfDownloadsDesc();
            case "PublishedDate":
                return productService.findAllSortedByUploadDateDesc();
            case "UpdatedDate":
                return productService.findAllSortedByLastCommitDateDesc();
            default:
                return null;
        }
    }

    @GetMapping("/filter")
    public @ResponseBody
    List<Product> findTop10Products(@RequestParam String top10) {
        switch (top10) {
            case "Newest":
                return productService.findTop10SortedByUploadDateDesc();
            case "Downloaded":
                return productService.findTop10SortedByNumberOfDownloadsDesc();
            default:
                return null;
        }
    }
}
