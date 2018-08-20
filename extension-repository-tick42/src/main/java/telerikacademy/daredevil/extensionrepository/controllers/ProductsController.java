package telerikacademy.daredevil.extensionrepository.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telerikacademy.daredevil.extensionrepository.models.Product;
import telerikacademy.daredevil.extensionrepository.services.base.ProductsService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public List<Product> listAllProducts() {
        return productsService.listAllProducts();
    }

//    @GetMapping("/")
//    public ResponseEntity<List<Product>> listAll(){
//        List<Product> products = productsService.listAllProducts();
//
//        ResponseEntity<List<Product>> responseEntity = new ResponseEntity(products, HttpStatus.OK);
//
//        return responseEntity;
//    }

    @GetMapping(value = "/{id}")
    public Product findById(@PathVariable("id") String idString){
        long id = Long.parseLong(idString);
        //???Validation
        return productsService.findProductById(id);
    }

    @PostMapping(value = "/add")
    public void addProduct(@RequestBody Product product) {
        productsService.saveProductIntoDB(product);
    }

    @PutMapping(value = "/{id}")
    public void updateProduct(@PathVariable("id") String idString, @RequestBody Product updateProduct) {
        long id = Long.parseLong(idString);
        productsService.updateProduct(id, updateProduct);
    }

    @DeleteMapping(value = "/{id}")
    public void removeProduct(@PathVariable("id") String idString) {
        long id = Long.parseLong(idString);
        productsService.deleteProduct(id);
    }

    @GetMapping("/sortByNameAsc")
    public List<Product> orderProductsByName() {
        return productsService.getProductsOrderByNameAsc();
    }

    @GetMapping("/sortByLastCommitDate")
    public List<Product> orderProductsByLastCommitDate() {
        return productsService.getProductOrderByLastCommitDate();
    }
}
