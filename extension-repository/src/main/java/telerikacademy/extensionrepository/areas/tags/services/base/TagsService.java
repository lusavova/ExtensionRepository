package telerikacademy.extensionrepository.areas.tags.services.base;

import telerikacademy.extensionrepository.areas.products.models.Product;
import telerikacademy.extensionrepository.areas.tags.models.Tag;
import telerikacademy.extensionrepository.areas.tags.models.dto.TagDTO;

import java.util.List;

public interface TagsService {
    List<Tag> listAll();

    Tag add(TagDTO tagDTO);

    void delete(long id);

    void update(Tag tag);

    List<Tag> addTags(List<TagDTO> tags);

    List<Product> listAllProducts(String tagname);
}
