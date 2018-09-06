package telerikacademy.extensionrepository.areas.tags.models.dto;

import javax.validation.constraints.NotNull;

public class TagDTO {
    @NotNull
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
