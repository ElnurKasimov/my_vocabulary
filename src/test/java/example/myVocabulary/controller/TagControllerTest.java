package example.myVocabulary.controller;

import example.myVocabulary.dto.WordResponseForCRUD;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Test
    @DisplayName("Test that GET  /tags/  works correctly")
    void getAllTags() throws Exception {
        Tag mockTag1 = new Tag(1L, "tag1", new ArrayList<>());
        Tag mockTag2 = new Tag(2L, "tag2", new ArrayList<>());
        Word mockWord1 = new Word(1L, "word1", "слово1", "", mockTag1);
        Word mockWord2 = new Word(2L, "word2", "слово2", "", mockTag1);
        Word mockWord3 = new Word(3L, "word3", "слово3", "", mockTag2);
        Word mockWord4 = new Word(4L, "word4", "слово4", "", mockTag2);
        List<Word> words = new ArrayList<>();
        words.add(mockWord1);
        words.add(mockWord2);
        words.add(mockWord3);
        words.add(mockWord4);
//        when(wordService.getAll()).thenReturn(words);
//        List<WordResponseForCRUD> expected = words.stream()
//                .map(wordTransformer::fromEntityForCRUD)
//                .toList();
//        this.mockMvc
//                .perform(get("/words/"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("word/list"))
//                .andExpect(model().attributeExists("words"))
//                .andExpect(model().attribute("words", hasSize(4)))
//                .andExpect(model().attribute("words", equalTo(expected)));
//        verify(wordService, times(1)).getAll();
    }


    @Test
    void getTag() {
    }

    @Test
    void getCreateTag() {
    }

    @Test
    void postCreateTag() {
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