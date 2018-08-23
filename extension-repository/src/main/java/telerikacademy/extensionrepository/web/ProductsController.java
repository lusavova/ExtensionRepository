package telerikacademy.extensionrepository.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telerikacademy.extensionrepository.models.Product;
import telerikacademy.extensionrepository.models.User;
import telerikacademy.extensionrepository.services.base.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> listAllProducts(){
        return productService.listAllProducts();
    }

    @GetMapping(value = "/{id}")
    public Product findById(@PathVariable("id") long id){
        return productService.findById(id);
    }

    @PostMapping("/add")
    public @ResponseBody Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @PutMapping(value = "update")
    public @ResponseBody Product updateProduct(@RequestBody Product product, long id){
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable long id){
        productService.deleteProduct(id);
    }

    @GetMapping("/user/{id}")
    public List<Product> findAllUserProducts(@PathVariable long id){
        return productService.findAllUserProducts(id);
    }


}
