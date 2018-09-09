package telerikacademy.extensionrepository.mapper;

import org.springframework.stereotype.Component;
import telerikacademy.extensionrepository.models.Tag;
import telerikacademy.extensionrepository.models.dto.TagDTO;

@Component
public class TagDTOMapper {
    public Tag mapTagDTOToTag(TagDTO tagDTO){
        Tag tag = new Tag();
        tag.setTagname(tagDTO.getTagname().trim().replaceAll("\\s+", ""));
        return tag;
    }
}
