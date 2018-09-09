package telerikacademy.extensionrepository.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telerikacademy.extensionrepository.models.Product;
import telerikacademy.extensionrepository.services.base.ProductService;
import telerikacademy.extensionrepository.models.Tag;
import telerikacademy.extensionrepository.models.dto.TagDTO;
import telerikacademy.extensionrepository.services.base.TagsService;

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
    void addProductTags(@PathVariable("id") long id, @RequestBody List<TagDTO> inputTags){
        Product product = productService.findById(id);
        List<Tag> tags = tagsService.addTags(inputTags);
        product.setTags(tags);
    }
}
