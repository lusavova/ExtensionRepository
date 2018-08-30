package telerikacademy.extensionrepository.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telerikacademy.extensionrepository.models.DTO.ProductDTO;
import telerikacademy.extensionrepository.models.Product;
import telerikacademy.extensionrepository.models.Tag;
import telerikacademy.extensionrepository.services.base.ProductService;
import telerikacademy.extensionrepository.services.base.TagsService;


import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private ProductService productService;
    private TagsService tagsService;

    @Autowired
    public ProductsController(ProductService productService, TagsService tagsService) {
        this.productService = productService;
        this.tagsService = tagsService;
    }

    @GetMapping("/")
    public List<Product> listAllProducts() {
        return productService.listAllProducts();
    }

    @GetMapping(value = "/{id}")
    public Product getById(@PathVariable("id") long id) {
        return productService.findById(id);
    }

    @PostMapping("/add")
    public @ResponseBody
    Product addProduct(@RequestBody ProductDTO product) {
        return productService.addProduct(product);
    }

    @PostMapping("/add/{id}/tags")
    public @ResponseBody
    void addProductTags(@PathVariable("id") long id, @RequestBody List<Tag> tags){
        for (Tag tag: tags){
            System.out.println(tag.getTagName());
        }

        Product product = productService.findById(id);
        tagsService.addTags(tags);
        //???
        product.setTags(tags);
    }

    @PutMapping(value = "/update/{id}")
    public @ResponseBody
    Product updateProduct(@RequestBody Product product, @PathVariable long id) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
    }
}
