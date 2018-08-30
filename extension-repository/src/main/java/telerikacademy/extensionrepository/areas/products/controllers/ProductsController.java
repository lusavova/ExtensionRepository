package telerikacademy.extensionrepository.areas.products.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telerikacademy.extensionrepository.areas.products.exeptions.ProductNotFoundExeption;
import telerikacademy.extensionrepository.areas.products.models.dto.ProductDTO;
import telerikacademy.extensionrepository.areas.products.models.Product;
import telerikacademy.extensionrepository.areas.products.services.base.ProductService;
import telerikacademy.extensionrepository.exceptions.InvalidArgumentExeption;

import java.util.List;

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
    public Product addProduct(@RequestBody ProductDTO product) {
        return productService.addProduct(product);
    }

    @PutMapping(value = "/update/{id}")
    @ResponseBody
    public Product updateProduct(@RequestBody Product product, @PathVariable long id) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
    }

    @ExceptionHandler(ProductNotFoundExeption.class)
    public String catchProductNotFoundExeption(){
        return "Product Not Found!";
    }

    @ExceptionHandler(InvalidArgumentExeption.class)
    public String catchInvalidArgumentExeption(){

        return "Invalid argument";
    }
}
