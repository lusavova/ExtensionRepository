package telerikacademy.extensionrepository.areas.tags.services.base;

import telerikacademy.extensionrepository.areas.products.models.Product;
import telerikacademy.extensionrepository.areas.tags.models.Tag;

import java.util.List;

public interface TagsService {
    List<Tag> listAll();

    Tag add(Tag tag);

    void delete(long id);

    void addTags(List<Tag> tags);

    List<Product> listAllProducts(String tagname);
}
