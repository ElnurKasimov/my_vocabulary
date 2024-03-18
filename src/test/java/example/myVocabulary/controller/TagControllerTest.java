package example.myVocabulary.controller;

import example.myVocabulary.dto.*;
import example.myVocabulary.model.Tag;
import example.myVocabulary.model.Word;
import example.myVocabulary.service.TagService;
import example.myVocabulary.service.WordService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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

    @Test
    @DisplayName("Test that GET  /tags/  works correctly")
    void getAllTags() throws Exception {
        Tag mockTag1 = new Tag(1L, "tag1", new ArrayList<>());
        Tag mockTag2 = new Tag(2L, "tag2", new ArrayList<>());
        List<Tag> tags = new ArrayList<>();
        tags.add(mockTag1);
        tags.add(mockTag2);
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
        Tag mockTag = new Tag(1L, "tag1", new ArrayList<>());
        Word mockWord1 = new Word(1L, "word1", "слово1", "", mockTag);
        Word mockWord2 = new Word(2L, "word2", "слово2", "", mockTag);
        List<Word> words = new ArrayList<>();
        words.add(mockWord1);
        words.add(mockWord2);
        mockTag.setWords(words);
        when(tagService.readById(1)).thenReturn(mockTag);
        List<WordResponseForCRUD> expected = words.stream()
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
        Tag mockTag1 = new Tag(1L, "tag1", new ArrayList<>());
        Tag mockTag2 = new Tag(2L, "tag2", new ArrayList<>());
        List<Tag> tags = new ArrayList<>();
        tags.add(mockTag1);
        tags.add(mockTag2);
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
    @DisplayName("Test that POST /tags/create  works correctly")
    void postCreateTag() throws Exception {
        TagRequest tagRequest = new TagRequest();
        tagRequest.setName("mockTag");
        Tag mockTag = tagTransformer.toEntityFromRequest(tagRequest);
//        when(tagService.readByName("mockTag")).thenReturn(mockTag);
        mockMvc.perform(post("/tags/create")
                        .param("tagRequest", String.valueOf(tagRequest)))
                .andExpect(status().is3xxRedirection())
//                .andExpect(flash().attribute("translateDirection", "translateDirection"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/tags/create/1"));
        verify(tagService, times(1)).readByName("mockTag1");

    }

    @Test
    void postDeleteTag() {
    }

    @Test
    void getUpdateTag() {
    }

    @Test
    void postUpdateTag() {
    }
}