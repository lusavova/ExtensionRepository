package telerikacademy.extensionrepository.services;

import org.junit.Assert;
import org.junit.Test;
import telerikacademy.extensionrepository.areas.tags.models.Tag;
import telerikacademy.extensionrepository.areas.tags.models.dto.TagDTO;

public class TagDTOMapperTests {
    @Test
    public void mapTagDTOToTag_shouldMapAndReturnTag(){
        String tagname = "testTagname";
        Tag tag = new Tag();
        TagDTO tagDTO = new TagDTO(tagname);
        tag.setTagname(tagDTO.getTagname());
        Assert.assertEquals(tagname, tag.getTagname());
    }
}
