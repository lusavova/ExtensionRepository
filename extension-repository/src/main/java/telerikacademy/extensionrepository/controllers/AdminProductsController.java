package telerikacademy.extensionrepository.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telerikacademy.extensionrepository.enums.ProductStatus;
import telerikacademy.extensionrepository.models.Product;
import telerikacademy.extensionrepository.services.base.ProductService;

import java.util.List;

@RestController
@RequestMapping("/admin/products")
public class AdminProductsController {
    private ProductService productService;

    @Autowired
    public AdminProductsController(ProductService productService) {
        this.productService = productService;
    }

    @PutMapping("/approve/{id}")
    @ResponseBody
    public void approveProduct(@PathVariable("id") long id) {
        productService.changeProductStatus(id, ProductStatus.ENABLED.name());
    }

    @PutMapping("/disable/{id}")
    @ResponseBody
    public void disableProduct(@PathVariable("id") long id) {
        productService.changeProductStatus(id, ProductStatus.DISABLED.name());
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void deleteProduct(@PathVariable("id") long id) {
        productService.deleteProduct(id);
    }

    @PutMapping("/edit")
    @ResponseBody
    public void editProduct(Product product) {
        productService.updateProduct(product);
    }

    @PutMapping("/feature/{id}")
    @ResponseBody
    public void featureProduct(@PathVariable("id") long id) {
        productService.changeFeatureProductStatus(id, true);
    }

    @PutMapping("/unfeature/{id}")
    @ResponseBody
    public void unfeatureProduct(@PathVariable("id") long id) {
        productService.changeFeatureProductStatus(id, false);
    }

    @GetMapping("/listAll")
    public List<Product> listAllProducts(){
        return productService.listAllProducts();
    }

    @GetMapping("/listAll/disabled")
    public List<Product> listAllDisabledProducts() {
        return productService.listAllDisabledProducts();
    }

    @GetMapping("/listAll/active")
    public List<Product> listAllActiveProducts() {
        return productService.listAllActiveProducts();
    }

    @GetMapping("/listAll/pending")
    public List<Product> listAllPendingProducts() {
        return productService.listAllPendingProducts();
    }
}
