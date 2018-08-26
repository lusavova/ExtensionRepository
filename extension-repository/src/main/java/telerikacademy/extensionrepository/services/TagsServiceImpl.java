package telerikacademy.extensionrepository.services;

import org.springframework.stereotype.Service;
import telerikacademy.extensionrepository.data.TagsRepository;
import telerikacademy.extensionrepository.models.Tag;
import telerikacademy.extensionrepository.services.base.TagsService;

import java.util.List;

@Service
public class TagsServiceImpl implements TagsService {
    private TagsRepository tagsRepository;

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
}
