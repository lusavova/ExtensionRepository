package telerikacademy.extensionrepository.areas.tags.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telerikacademy.extensionrepository.areas.products.models.Product;
import telerikacademy.extensionrepository.areas.tags.exeptions.TagNotFoundExeption;
import telerikacademy.extensionrepository.areas.tags.models.Tag;
import telerikacademy.extensionrepository.areas.tags.models.dto.TagDTO;
import telerikacademy.extensionrepository.areas.tags.services.base.TagsService;
import telerikacademy.extensionrepository.exceptions.FormatExeption;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagsController  {
    private TagsService tagsService;

    @Autowired
    public TagsController(TagsService tagsService) {
        this.tagsService = tagsService;
    }

    @GetMapping
    public List<Tag> listAllTags(){
        return tagsService.listAll();
    }

    @PostMapping("/add")
    public @ResponseBody Tag add(@Valid TagDTO tagDTO){
        return tagsService.add(tagDTO);
    }

    @PutMapping("/update")
    public void update(@RequestBody Tag tag){
        tagsService.update(tag);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id){
        tagsService.delete(id);
    }

    @PostMapping("/products")
    public List<Product> listAllProducts(String tagname){
        return tagsService.listAllProducts(tagname);
    }

    @ExceptionHandler(TagNotFoundExeption.class)
    public String catchUserNotFoundExeption(){
        return "Tag Not Found.";
    }


    @ExceptionHandler
    public String catchFormatExeptions(FormatExeption ex) {
        return ex.getMessage();
    }

    @ExceptionHandler
    public String catchIllegalArgumentExceptions(IllegalArgumentException ex) {
        return ex.getMessage();
    }
}
