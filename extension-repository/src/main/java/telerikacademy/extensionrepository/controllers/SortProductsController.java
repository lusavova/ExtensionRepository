package telerikacademy.extensionrepository.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telerikacademy.extensionrepository.models.Product;
import telerikacademy.extensionrepository.services.base.SortProductsService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class SortProductsController {
    private SortProductsService sortProductsService;

    @Autowired
    public SortProductsController(SortProductsService sortProductsService) {
        this.sortProductsService = sortProductsService;
    }

    @GetMapping
    public @ResponseBody
    List<Product> sortProductsByParam(@RequestParam String sortBy) {
        switch (sortBy) {
            case "Name":
                return sortProductsService.findAllSortedByName();
            case "Downloads":
                return sortProductsService.findAllSortedByNumberOfDownloadsDesc();
            case "PublishedDate":
                return sortProductsService.findAllSortedByUploadDateDesc();
            case "UpdatedDate":
                return sortProductsService.findAllSortedByLastCommitDateDesc();
            default:
                return sortProductsService.findAllSortedByName();
        }
    }

    @GetMapping("/filter")
    public @ResponseBody
    List<Product> findTop10Products(@RequestParam String topN) {
        int number = 10;
        switch (topN) {
            case "Featured":
                return null;
            case "Newest":
                return sortProductsService.findTopNSortedByUploadDateDesc(number);
            case "Downloaded":
                return sortProductsService.findTopNSortedByNumberOfDownloadsDesc(number);
            default:
                // THROW EXEPTION???
                return null;
        }
    }
}
