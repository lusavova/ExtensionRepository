package telerikacademy.extensionrepository.areas.tags.models.dto;

import telerikacademy.extensionrepository.anotations.Unique;
import telerikacademy.extensionrepository.areas.tags.services.TagsServiceImpl;

import javax.validation.constraints.NotNull;

public class TagDTO {
    @NotNull
    @Unique(service = TagsServiceImpl.class, fieldName = "tagname", message = "Tag already exists.")
    private String tagname;

    public TagDTO(String tagname) {
        this.tagname = tagname;
    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }
}
