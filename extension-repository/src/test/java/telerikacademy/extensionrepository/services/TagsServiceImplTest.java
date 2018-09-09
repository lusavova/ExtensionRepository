package telerikacademy.extensionrepository.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import telerikacademy.extensionrepository.mapper.TagDTOMapper;
import telerikacademy.extensionrepository.data.ProductsRepository;
import telerikacademy.extensionrepository.data.TagsRepository;
import telerikacademy.extensionrepository.models.Tag;
import telerikacademy.extensionrepository.models.dto.TagDTO;
import telerikacademy.extensionrepository.exceptions.FormatExeption;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TagsServiceImplTest {
    private TagsServiceImpl tagsService;
    private TagsRepository mockTagsRepository = Mockito.mock(TagsRepository.class);
    private TagDTOMapper mockMapper = Mockito.mock(TagDTOMapper.class);
    private ProductsRepository mockProductsRepository = Mockito.mock(ProductsRepository.class);

    @Before
    public void setUp() {
        tagsService = new TagsServiceImpl(mockTagsRepository, mockMapper, mockProductsRepository);
    }

    @Test
    public void listAll_should_return3Tags_when_countEquals3() {
        List<Tag> listOfTags = Arrays.asList(
                new Tag(),
                new Tag(),
                new Tag()
        );

        Mockito.when(mockTagsRepository.findAll())
                .thenReturn(listOfTags);

        List<Tag> resultTags = tagsService.listAll();

        Assert.assertEquals(3, resultTags.size());
    }

    @Test
    public void add_should_addTagIntoDB_nominalCase() {
        Tag tag = new Tag();
        tag.setTagname("testTag");
        TagDTO tagDTO = new TagDTO("testTag");
        Mockito.when(mockMapper.mapTagDTOToTag(tagDTO)).thenReturn(tag);
        Mockito.when(mockTagsRepository.saveAndFlush(tag)).thenReturn(tag);

        Tag addedTag = tagsService.add(tagDTO);
        Assert.assertSame(addedTag, tag);
    }

    @Test(expected = FormatExeption.class)
    public void add_should_throwExeption_when_tagNameIsNotValid() {
        String TEST_TAG = "test!!!!";
        Tag tag = new Tag();
        tag.setTagname(TEST_TAG);
        TagDTO tagDTO = new TagDTO(TEST_TAG);
        Mockito.when(mockMapper.mapTagDTOToTag(tagDTO)).thenReturn(tag);
        Mockito.when(mockTagsRepository.saveAndFlush(tag)).thenReturn(tag);
        tagsService.add(tagDTO);
    }

    @Test
    public void delete_should_deleteTagFromDB_when_tagIdExists() {
        long id = 1;
        Tag tag = new Tag();
        Mockito.when(mockTagsRepository.findById(id)).thenReturn(java.util.Optional.of(tag));

        Mockito.doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);
            Assert.assertEquals(id, arg0);
            return null;
        }).when(mockTagsRepository).deleteById(id);

        tagsService.delete(id);
    }

    @Test
    public void addTags_shouldAdd3Tags_when_3TagsAreGiven() {
        List<TagDTO> tagDTOs = Arrays.asList(
                new TagDTO("tag1"),
                new TagDTO("tag2"),
                new TagDTO("tag3")
        );

        List<Tag> expectedTags = new ArrayList<>();

        for (int i = 0; i < tagDTOs.size(); i++) {
            expectedTags.add(new Tag());
            Mockito.when(mockMapper.mapTagDTOToTag(tagDTOs.get(i))).thenReturn(expectedTags.get(i));
            Mockito.when(mockTagsRepository.saveAndFlush(expectedTags.get(i))).thenReturn(expectedTags.get(i));
        }

        List<Tag> actualTags = tagsService.addTags(tagDTOs);
        Assert.assertArrayEquals(expectedTags.toArray(), actualTags.toArray());
    }
}
