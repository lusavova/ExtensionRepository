package telerikacademy.extensionrepository.areas.tags.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import telerikacademy.extensionrepository.areas.files.models.File;
import telerikacademy.extensionrepository.areas.mapper.TagDTOMapper;
import telerikacademy.extensionrepository.areas.products.data.ProductsRepository;
import telerikacademy.extensionrepository.areas.tags.data.TagsRepository;
import telerikacademy.extensionrepository.areas.products.models.Product;
import telerikacademy.extensionrepository.areas.tags.exeptions.TagNotFoundExeption;
import telerikacademy.extensionrepository.areas.tags.models.Tag;
import telerikacademy.extensionrepository.areas.tags.models.dto.TagDTO;
import telerikacademy.extensionrepository.areas.tags.services.base.TagsService;
import telerikacademy.extensionrepository.constants.Constants;
import telerikacademy.extensionrepository.exceptions.FormatExeption;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class TagsServiceImpl implements TagsService {
    private TagsRepository tagsRepository;
    private TagDTOMapper mapper;
    private ProductsRepository productsRepository;

    @Autowired
    public TagsServiceImpl(TagsRepository tagsRepository,
                           TagDTOMapper mapper,
                           ProductsRepository productsRepository) {
        this.tagsRepository = tagsRepository;
        this.mapper = mapper;
        this.productsRepository = productsRepository;
    }

    @Override
    public List<Tag> listAll() {
        return tagsRepository.findAll();
    }

    @Override
    public Tag add(TagDTO tagDTO) {
        if (!isTagValid(tagDTO.getTagname())) {
            throw new FormatExeption("Invalid tag format.");
        }

        Tag tag = mapper.mapTagDTOToTag(tagDTO);
        return tagsRepository.saveAndFlush(tag);
    }

    @Override
    public void delete(long id) {
        Tag tag = findById(id);
        tagsRepository.delete(tag);
    }

    @Override
    public void update(TagDTO tagDTO) {
        if (!isTagValid(tagDTO.getTagname())) {
            throw new FormatExeption("Invalid tag format");
        }
        Tag tag = mapper.mapTagDTOToTag(tagDTO);
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
        List<Product> allProducts = productsRepository.findAll();
        List<Product> filterProducts = allProducts.stream()
                .filter(pr -> {
                    Set<String> tags = pr.getTags()
                            .stream()
                            .map(Tag::getTagname)
                            .collect(Collectors.toSet());
                    return tags.contains(tagname);
                }).collect(Collectors.toList());
        return filterProducts;
    }

    private Tag findById(long id) {
        return tagsRepository
                .findById(id)
                .orElseThrow(() -> new TagNotFoundExeption("Cannot find tag with id = " + id));
    }

    private boolean isTagValid(String tagName) {
        Pattern pattern = Pattern.compile(Constants.TAG_PATTERN);
        Matcher matcher = pattern.matcher(tagName);
        return matcher.find();
    }

    @Override
    public boolean fieldValueExists(Object value, String fieldName) {
        Assert.notNull(fieldName, String.format("%s already exist.", formatField(fieldName)));

        if (value == null) {
            return false;
        }

        return setOfValues(fieldName).contains(value);
    }

    private Set<Object> setOfValues(Object fieldName) {
        Set<Object> values;

        switch (fieldName.toString()) {
            case "tagname":
                values = tagsRepository.findAll().stream()
                        .map(Tag::getTagname)
                        .collect(Collectors.toSet());
                break;
            default:
                throw new IllegalArgumentException("No such tag field.");
        }

        return values;
    }

    private String formatField(String fieldName) {
        char firstLetter = fieldName.toUpperCase().charAt(0);
        String resultLetters = fieldName.substring(1).toLowerCase();
        return firstLetter + resultLetters;
    }
}
