package telerikacademy.extensionrepository.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telerikacademy.extensionrepository.models.Tag;
import telerikacademy.extensionrepository.services.base.TagsService;

@RestController
@RequestMapping("/tags")
public class TagsController  {
    private TagsService tagsService;

    @Autowired
    public TagsController(TagsService tagsService) {
        this.tagsService = tagsService;
    }

    @GetMapping
    public void listAllTags(){
        tagsService.listAll();
    }

    @PostMapping("/add")
    public @ResponseBody Tag add(@RequestBody Tag tag){
        return tagsService.add(tag);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id){
        tagsService.delete(id);
    }
}
