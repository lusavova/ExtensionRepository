package telerikacademy.extensionrepository.areas.tags.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telerikacademy.extensionrepository.areas.tags.data.TagsRepository;
import telerikacademy.extensionrepository.areas.products.models.Product;
import telerikacademy.extensionrepository.areas.tags.exeptions.TagNotFoundExeption;
import telerikacademy.extensionrepository.areas.tags.models.Tag;
import telerikacademy.extensionrepository.areas.tags.models.dto.TagDTO;
import telerikacademy.extensionrepository.areas.tags.services.base.TagsService;
import telerikacademy.extensionrepository.exceptions.FormatExeption;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TagsServiceImpl implements TagsService {
    private static final String TAG_PATTERN = "[a-zA-Z0-9]{2,}";

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
    public Tag add(TagDTO tagDTO) {
        if (!isTagValid(tagDTO.getTagname())) {
            throw new FormatExeption("Invalid format");
        }
        if (tagAlreadyExist(tagDTO.getTagname())) {
            throw new IllegalArgumentException("Tag already exist.");
        }

        Tag tag = bindTagDTOToTag(tagDTO);
        return tagsRepository.saveAndFlush(tag);
    }

    @Override
    public void delete(long id) {
        Tag tag = findById(id);
        tagsRepository.delete(tag);
    }

    @Override
    public void update(Tag tag) {
        if (!isTagValid(tag.getTagname())) {
            throw new FormatExeption("Invalid format");
        }
        if (tagAlreadyExist(tag.getTagname())) {
            throw new IllegalArgumentException("Tag already exist.");
        }
        tagsRepository.saveAndFlush(tag);
    }

    @Override
    public List<Tag> addTags(List<TagDTO> inputTags) {
        List<Tag> tags = new ArrayList<>();

        for (TagDTO tag : inputTags) {
            Tag t = add(tag);
            tags.add(t);
        }
        return tags;
    }

    @Override
    public List<Product> listAllProducts(String tagname) {
        Tag tag = findTagByName(tagname);
        return tag.getProducts();
    }

    private Tag findById(long id) {
        return tagsRepository
                .findById(id)
                .orElseThrow(() -> new TagNotFoundExeption("Cannot find tag with id = " + id));
    }

    private Tag findTagByName(String tagname) {
        return tagsRepository
                .findAll()
                .stream()
                .filter(t -> t.getTagname().equals(tagname))
                .findFirst()
                .orElseThrow(() -> new TagNotFoundExeption("Cannot find tag with name: " + tagname));
    }

    private boolean tagAlreadyExist(String tagName) {
        Tag tag = tagsRepository.findTagByTagname(tagName);
        if (tag == null) {
            return false;
        }
        return true;
    }

    private boolean isTagValid(String tagName) {
        Pattern pattern = Pattern.compile(TAG_PATTERN);
        Matcher matcher = pattern.matcher(tagName);
        return matcher.find();
    }

    private Tag bindTagDTOToTag(TagDTO tagDTO) {
        Tag tag = new Tag();
        tag.setTagname(tagDTO.getTagname());
        return tag;
    }
}
