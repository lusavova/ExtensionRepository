package telerikacademy.extensionrepository.services.base;

import telerikacademy.extensionrepository.models.Tag;

import java.util.List;

public interface TagsService {
    List<Tag> listAll();

    Tag add(Tag tag);

    void delete(long id);
}
