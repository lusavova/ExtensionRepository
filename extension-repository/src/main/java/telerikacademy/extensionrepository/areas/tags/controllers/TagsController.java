package telerikacademy.extensionrepository.areas.tags.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telerikacademy.extensionrepository.areas.tags.models.Tag;
import telerikacademy.extensionrepository.areas.tags.services.base.TagsService;

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
    public @ResponseBody Tag add(@RequestBody Tag tag){
        return tagsService.add(tag);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id){
        tagsService.delete(id);
    }
}
