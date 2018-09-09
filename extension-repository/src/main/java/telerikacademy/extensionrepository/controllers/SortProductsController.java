package telerikacademy.extensionrepository.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import telerikacademy.extensionrepository.constants.Constants;
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
    ResponseEntity<List<Product>> findTop10Products(@RequestParam String topN) {
        int number = Constants.FILTER_PRODUCT_NUMBER;
        List<Product> products ;
        switch (topN) {
            case "Featured":
                products = sortProductsService.findTopNFeaturedProducts(number);
                return new ResponseEntity<>(products, HttpStatus.OK);
            case "Newest":
                products = sortProductsService.findTopNSortedByUploadDateDesc(number);
                return new ResponseEntity<>(products, HttpStatus.OK);
            case "Downloaded":
                products =sortProductsService.findTopNSortedByNumberOfDownloadsDesc(number);
                return new ResponseEntity<>(products, HttpStatus.OK);
            default:
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
