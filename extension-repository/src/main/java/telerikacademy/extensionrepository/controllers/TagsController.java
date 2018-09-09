package telerikacademy.extensionrepository.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import telerikacademy.extensionrepository.models.Product;
import telerikacademy.extensionrepository.models.Tag;
import telerikacademy.extensionrepository.models.dto.TagDTO;
import telerikacademy.extensionrepository.services.base.TagsService;

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
    public void update(@RequestBody @Valid TagDTO tag){
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

    @ExceptionHandler
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
    }
}
