package telerikacademy.extensionrepository.areas.products.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import telerikacademy.extensionrepository.areas.products.exeptions.ProductNotFoundExeption;
import telerikacademy.extensionrepository.areas.products.models.dto.ProductDTO;
import telerikacademy.extensionrepository.areas.products.models.Product;
import telerikacademy.extensionrepository.areas.products.services.base.ProductService;
import telerikacademy.extensionrepository.exceptions.FormatExeption;
import telerikacademy.extensionrepository.exceptions.InvalidArgumentExeption;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    @ResponseBody
    public List<Product> listAllProducts() {
        return productService.listAllProducts();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Product getById(@PathVariable("id") long id) {
        return productService.findById(id);
    }

    @PostMapping("/add")
    @ResponseBody
    public Product addProduct(@RequestBody @Valid ProductDTO product) {
        System.out.println("***************");
        System.out.println(product.toString());
        System.out.println("***************");

        return productService.addProduct(product);
    }

    @PutMapping(value = "/update")
    @ResponseBody
    public Product updateProduct(@RequestBody @Valid ProductDTO product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
    }

    @PostMapping("/githubRepo")
    public boolean checkGithubRepo(String repo) {
        return productService.githubRepoAlreadyExists(repo);
    }

    @ExceptionHandler
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return message;
    }
}
