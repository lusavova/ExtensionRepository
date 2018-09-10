package telerikacademy.extensionrepository.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import telerikacademy.extensionrepository.enums.ProductStatus;
import telerikacademy.extensionrepository.exceptions.IllegalOperationException;
import telerikacademy.extensionrepository.models.dto.ProductDTO;
import telerikacademy.extensionrepository.models.Product;
import telerikacademy.extensionrepository.services.base.ProductService;
import telerikacademy.extensionrepository.services.base.StorageService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private ProductService productService;
    private StorageService storageService;

    @Autowired
    public ProductsController(ProductService productService,
                              StorageService storageService) {
        this.productService = productService;
        this.storageService = storageService;
    }

    @GetMapping("/")
    @ResponseBody
    public List<Product> listAllProducts() {
        return productService.listAllActiveProducts();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Product getById(@PathVariable("id") long id) {
        return productService.findById(id);
    }

    @PostMapping("/add")
    @ResponseBody
    public Product addProduct(@RequestBody @Valid ProductDTO product) {
        return productService.addProduct(product);
    }

    @PutMapping(value = "/update/{id}")
    @ResponseBody
    public Product updateProduct(@RequestBody @Valid Product product, @PathVariable long id) {
        return productService.updateProduct(product, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/download/product/{id}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable("id") long id) {
        Product product = productService.findById(id);
        productService.increaseNumOfDownload(product.getId());

        if (product.getProductStatus().equals(ProductStatus.PENDING.name())){
            throw new IllegalOperationException("Cannot download file, because product is not approved.");
        }
        if (product.getProductStatus().equals(ProductStatus.DISABLED.name())){
            throw new IllegalOperationException("Cannot download file, because product is not enabled.");
        }
        Resource file = storageService.loadAsResource(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/githubRepo")
    public boolean checkGithubRepo(String repo) {
        return productService.githubRepoAlreadyExists(repo);
    }

    @ExceptionHandler
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
    }
}
