package telerikacademy.extensionrepository.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import telerikacademy.extensionrepository.models.Product;
import telerikacademy.extensionrepository.services.base.ProductService;

import java.util.List;

@RestController("/products")
public class SortProductsController {

    private ProductService productService;

    @Autowired
    public SortProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/sortBy", params = "param")
    @ResponseBody
    public List<Product> sortProbuctsByParam(
            @RequestParam("param") String param) {

        switch (param) {
            case "Name":
                return productService.findAllSortedByName();
            case "Downloads":
                return productService.findAllSortedByNumberOfDownloadsDesc();
            case "PublishedDate":
                return productService.findAllSortedByUploadDateDesc();
            case "UpdatedDate":
                return productService.findAllSortedByLastCommitDateDesc();
            //VALIDATION
            default:
                return null;
        }
    }
}
