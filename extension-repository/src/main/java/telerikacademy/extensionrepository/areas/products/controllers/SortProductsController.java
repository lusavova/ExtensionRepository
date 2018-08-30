package telerikacademy.extensionrepository.areas.products.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telerikacademy.extensionrepository.areas.products.models.Product;
import telerikacademy.extensionrepository.areas.products.services.base.SortProductsService;

import java.util.List;

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
                //THROW EXEPTION???
                return null;
        }
    }

    @GetMapping("/filter")
    public @ResponseBody
    List<Product> findTop10Products(@RequestParam String top10) {
        switch (top10) {
            case "Newest":
                return sortProductsService.findTop10SortedByUploadDateDesc();
            case "Downloaded":
                return sortProductsService.findTop10SortedByNumberOfDownloadsDesc();
            default:
                // THROW EXEPTION???
                return null;
        }
    }
}
