package telerikacademy.extensionrepository.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telerikacademy.extensionrepository.data.TagsRepository;
import telerikacademy.extensionrepository.models.Tag;
import telerikacademy.extensionrepository.services.base.TagsService;

import java.util.List;

@Service
public class TagsServiceImpl implements TagsService {
    private TagsRepository tagsRepository;

    @Autowired
    public TagsServiceImpl(TagsRepository tagsRepository) {
        this.tagsRepository = tagsRepository;
    }

    @Override
    public List<Tag> listAll() {
        return tagsRepository.findAll();
    }

    @Override
    public Tag add(Tag tag) {
        return tagsRepository.save(tag);
    }

    @Override
    public void delete(long id) {
        tagsRepository.deleteById(id);
    }

    @Override
    public void addTags(List<Tag> tags) {
        for (Tag tag: tags){
            tagsRepository.save(tag);
        }
    }
}
