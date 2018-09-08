package telerikacademy.extensionrepository.mapper;

import org.junit.Assert;
import org.junit.Test;
import telerikacademy.extensionrepository.areas.mapper.TagDTOMapper;
import telerikacademy.extensionrepository.areas.tags.models.Tag;
import telerikacademy.extensionrepository.areas.tags.models.dto.TagDTO;

public class TagDTOMapperTests {
    private TagDTOMapper mapper = new TagDTOMapper();

    @Test
    public void mapTagDTOToTag_shouldMapAndReturnTag(){
        String tagname = "testTagname";
        Tag expectedTag = new Tag();
        expectedTag.setTagname(tagname);
        TagDTO tagDTO = new TagDTO(tagname);

        Tag actualTag = mapper.mapTagDTOToTag(tagDTO);
        Assert.assertEquals(expectedTag.getTagname(), actualTag.getTagname());
    }
}
