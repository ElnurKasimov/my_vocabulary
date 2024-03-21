package example.myVocabulary.controller;

import example.myVocabulary.dto.*;
import example.myVocabulary.model.Tag;
import example.myVocabulary.model.Word;
import example.myVocabulary.service.TagService;
import example.myVocabulary.service.WordService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@SpringBootTest
@AutoConfigureMockMvc

class TagControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TagService tagService;

    @MockBean
    private WordService wordService;

    @Autowired
    private WordTransformer wordTransformer;

    @Autowired
    private TagTransformer tagTransformer;

    private static Tag mockTag1;
    private static Tag mockTag2;
    private static List<Tag> tags = new ArrayList<>();

    private static List<String> errors = new ArrayList<>();

    @BeforeAll
    private static void initTags() {
        List<Word> words1 = new ArrayList<>();
        List<Word> words2 = new ArrayList<>();
        mockTag1 = new Tag(1L, "tag1", words1);
        mockTag2 = new Tag(2L, "tag2", words2);
        Word mockWord1 = new Word(1L, "word1", "слово1", "", mockTag1);
        Word mockWord2 = new Word(2L, "word2", "слово2", "", mockTag1);
        Word mockWord3 = new Word(1L, "word1", "слово3", "", mockTag2);
        Word mockWord4 = new Word(2L, "word2", "слово4", "", mockTag2);
        words1.add(mockWord1);
        words1.add(mockWord2);
        words2.add(mockWord3);
        words2.add(mockWord4);
        tags.add(mockTag1);
        tags.add(mockTag2);
    }


    @Test
    @DisplayName("Test that GET  /tags/  works correctly")
    void getAllTags() throws Exception {
        when(tagService.getAll()).thenReturn(tags);
        this.mockMvc
                .perform(get("/tags/"))
                .andExpect(status().isOk())
                .andExpect(view().name("tag/tag-list"))
                .andExpect(model().attributeExists("tags", "errorId"))
                .andExpect(model().attribute("tags", hasSize(2)))
                .andExpect(model().attribute("tags", equalTo(tags)));
        verify(tagService, times(1)).getAll();
    }


    @Test
    @DisplayName("Test that GET /tags/{id}  works correctly")
    void getTag() throws Exception {
        when(tagService.readById(1)).thenReturn(mockTag1);
        List<WordResponseForCRUD> expected = mockTag1.getWords().stream()
            .map(wordTransformer::fromEntityForCRUD)
            .sorted()
            .toList();
        this.mockMvc
            .perform(get("/tags/{id}", 1L))
            .andExpect(status().isOk())
            .andExpect(view().name("tag/tag-words"))
            .andExpect(model().attributeExists("words", "tagName"))
            .andExpect(model().attribute("words", hasSize(2)))
            .andExpect(model().attribute("words", equalTo(expected)))
            .andExpect(model().attribute("tagName", "tag1"));
        verify(tagService, times(1)).readById(1L);
    }

    @Test
    @DisplayName("Test that GET /tags/create  works correctly")
    void getCreateTag() throws Exception {
        when(tagService.getAll()).thenReturn(tags);
        List<TagResponse> tagsResponse = tags.stream()
                .map(tagTransformer::fromEntity)
                .toList();
        this.mockMvc
                .perform(get("/tags/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("tag/create"))
                .andExpect(model().attributeExists("tags", "tagRequest"))
                .andExpect(model().attribute("tags", hasSize(2)))
                .andExpect(model().attribute("tags", equalTo(tagsResponse)))
                .andExpect(model().attribute("tagRequest", equalTo(new TagRequest())));
        verify(tagService, times(1)).getAll();
    }

    @Test
    @DisplayName("Test that POST /tags/create  works correctly with valid data")
    void postCreateTagCorrect() throws Exception {
        TagRequest tagRequest = new TagRequest();
        tagRequest.setName("mockTag");
        Tag mockTag = tagTransformer.toEntityFromRequest(tagRequest);
        mockTag.setId(1L);
        when(tagService.create(any())).thenReturn(mockTag);
        BindingResult result = mock(BindingResult.class);
        when(tagService.getTagErrors(mockTag.getName(),result)).thenReturn(errors);
        mockMvc.perform(post("/tags/create")
                        .flashAttr("tagRequest", tagRequest))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/tags/1"));
        verify(tagService, times(1)).create(any());
    }

    @Test
    @DisplayName("Test that POST /tags/create  works correctly with invalid data")
    void postCreateTagIncorrect() throws Exception {
        TagRequest tagRequest = new TagRequest();
        tagRequest.setName("name");
        Tag mockTag = tagTransformer.toEntityFromRequest(tagRequest);
        mockTag.setId(1L);
        when(tagService.create(any())).thenReturn(mockTag);
        BindingResult result = mock(BindingResult.class);
//        when(result.hasErrors()).thenReturn(true);
        errors.add("error1");
        errors.add("error2");
        when(tagService.getTagErrors(tagRequest.getName(),result)).thenReturn(errors);
        List<TagResponse> tagResponses = tags.stream()
                .map(tagTransformer::fromEntity)
                .toList();
        mockMvc.perform(post("/tags/create")
                        .flashAttr("tagRequest", tagRequest)
                        .flashAttr("bindingResult", result))
                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("/tag/create"))
                .andExpect(model().attributeExists("errors", "tags"))
                .andExpect(model().attribute("errors", equalTo(errors)))
                .andExpect(model().attribute("tags",equalTo(tagResponses)));
        verify(tagService, times(1)).create(any());
        verify(tagService, times(1)).getAll();
    }



    @Test
    @DisplayName("Test that GET /tags/{id}/delete  works correctly with empty field words")
    void postDeleteTagWithoutWords() throws Exception {
        Tag mockTag = new Tag(1L, "tag", new ArrayList<>());
        when(tagService.readById(1L)).thenReturn(mockTag);
        this.mockMvc
                .perform(get("/tags/{id}/delete", 1L))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/tags/"))
                .andExpect(flash().attributeCount(0));
        ArgumentCaptor<Long> valueCapture = ArgumentCaptor.forClass(Long.class);
        doNothing().when(tagService).delete(valueCapture.capture());
        assertEquals(1L,mockTag.getId());
    }

    @Test
    @DisplayName("Test that GET /tags/{id}/delete  works correctly with nonempty field words")
    void postDeleteTagWithWords() throws Exception {
        Tag mockTag = new Tag(1L, "tag", new ArrayList<>());
        Word mockWord1 = new Word(1L, "word1", "слово1", "", mockTag);
        Word mockWord2 = new Word(2L, "word2", "слово2", "", mockTag);
        List<Word> words = new ArrayList<>();
        words.add(mockWord1);
        words.add(mockWord2);
        mockTag.setWords(words);
        when(tagService.readById(1L)).thenReturn(mockTag);
        this.mockMvc
                .perform(get("/tags/{id}/delete", 1L))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/tags/"))
                .andExpect(flash().attributeExists("errorId"))
                .andExpect(flash().attribute("errorId", 1L));
        ArgumentCaptor<Long> valueCapture = ArgumentCaptor.forClass(Long.class);
        doNothing().when(tagService).delete(valueCapture.capture());
        assertEquals(1L,mockTag.getId());
    }

    @Test
    @DisplayName("Test that GET /tags/{id}/update  works correctly")
    void getUpdateTag() throws Exception {
        Tag mockTag1 = new Tag(1L, "tag1", new ArrayList<>());
        Tag mockTag2 = new Tag(2L, "tag2", new ArrayList<>());
        List<Tag> tags = new ArrayList<>();
        tags.add(mockTag1);
        tags.add(mockTag2);
        when(tagService.readById(1L)).thenReturn(mockTag1);
        when(tagService.getAll()).thenReturn(tags);
        List<TagResponse> tagResponses = tags.stream()
                .map(tagTransformer::fromEntity)
                .collect(Collectors.toList());
        this.mockMvc
                .perform(get("/tags/{id}/update",1L))
                .andExpect(status().isOk())
                .andExpect(view().name("tag/update"))
                .andExpect(model().attributeExists("tag", "tags"))
                .andExpect(model().attribute("tag", mockTag1))
                .andExpect(model().attribute("tags",equalTo(tagResponses)));
        verify(tagService, times(1)).readById(1L);
        verify(tagService, times(1)).getAll();
    }

    @Test
    @DisplayName("Test that GET /tags/{id}/update  works correctly")
    void postUpdateTag() throws Exception {
        Tag mockTag1 = new Tag(1L, "tag1", new ArrayList<>());
        Tag mockTag2 = new Tag(2L, "tag2", new ArrayList<>());
        List<Tag> tags = new ArrayList<>();
        tags.add(mockTag1);
        tags.add(mockTag2);
        when(tagService.readById(1L)).thenReturn(mockTag1);
        when(tagService.getAll()).thenReturn(tags);
        Tag mockTag = new Tag(1L, "tag", new ArrayList<>());
        BindingResult result = mock(BindingResult.class);
//        when(result.hasErrors()).thenReturn(true);
        List<String> errors = new ArrayList<>();
        errors.add("error1");
        errors.add("error2");
        when(tagService.getTagErrors(mockTag.getName(),result)).thenReturn(errors);
        List<TagResponse> tagResponses = tags.stream()
                .map(tagTransformer::fromEntity)
                .collect(Collectors.toList());
        this.mockMvc
                .perform(get("/tags/{id}/update",1L))
                .andExpect(status().isOk())
                .andExpect(view().name("tag/update"))
                .andExpect(model().attributeExists("errors", "tags"))
                .andExpect(model().attribute("errors", equalTo(errors)))
                .andExpect(model().attribute("tags",equalTo(tagResponses)));
        verify(tagService, times(1)).readById(1L);
        verify(tagService, times(1)).getAll();
    }
}