package telerikacademy.extensionrepository.areas.products.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telerikacademy.extensionrepository.areas.products.exeptions.ProductNotFoundExeption;
import telerikacademy.extensionrepository.areas.products.models.Product;
import telerikacademy.extensionrepository.areas.products.services.base.ProductService;
import telerikacademy.extensionrepository.areas.tags.models.Tag;
import telerikacademy.extensionrepository.areas.tags.services.base.TagsService;

import java.util.List;

@RestController
@RequestMapping("/products/tags")
public class ProductsTagsController {
    private ProductService productService;
    private TagsService tagsService;

    @Autowired
    public ProductsTagsController(ProductService productService, TagsService tagsService) {
        this.productService = productService;
        this.tagsService = tagsService;
    }

    @PostMapping("/add/{id}")
    public @ResponseBody
    void addProductTags(@PathVariable("id") long id, @RequestBody List<Tag> tags){
        Product product = productService.findById(id);
        tagsService.addTags(tags);
        product.setTags(tags);
    }

    @ExceptionHandler(ProductNotFoundExeption.class)
    public String catchProductNotFoundExeption(){
        return "Product Not Found!";
    }
}
