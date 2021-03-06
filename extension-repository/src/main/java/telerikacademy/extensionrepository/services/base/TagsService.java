package telerikacademy.extensionrepository.services.base;

import telerikacademy.extensionrepository.models.Product;
import telerikacademy.extensionrepository.models.Tag;
import telerikacademy.extensionrepository.models.dto.TagDTO;
import telerikacademy.extensionrepository.common.FieldValueExists;

import java.util.List;

public interface TagsService extends FieldValueExists {
    List<Tag> listAll();

    Tag add(TagDTO tagDTO);

    void delete(long id);

    void update(TagDTO tag);

    List<Tag> addTags(List<TagDTO> tags);

    List<Product> listAllProducts(String tagname);
}
