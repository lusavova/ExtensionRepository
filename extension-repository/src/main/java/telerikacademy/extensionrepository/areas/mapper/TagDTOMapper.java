package telerikacademy.extensionrepository.areas.mapper;

import org.springframework.stereotype.Component;
import telerikacademy.extensionrepository.areas.tags.models.Tag;
import telerikacademy.extensionrepository.areas.tags.models.dto.TagDTO;

@Component
public class TagDTOMapper {
    public Tag mapTagDTOToTag(TagDTO tagDTO){
        Tag tag = new Tag();
        tag.setTagname(tagDTO.getTagname().trim().replaceAll("\\s+", ""));
        return tag;
    }
}
