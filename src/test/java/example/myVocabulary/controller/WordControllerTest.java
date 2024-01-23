package example.myVocabulary.controller;

import example.myVocabulary.model.Tag;
import example.myVocabulary.model.Word;
import example.myVocabulary.service.TagService;
import example.myVocabulary.service.WordService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WordControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TagService tagService;
    @Autowired
    private WordService wordService;

    @Test
    @DisplayName("Test that GET  /words/  works correctly")
    void getAllWords() throws Exception {
        this.mockMvc
                .perform(get("/words/"))
                .andExpect(status().isOk())
                .andExpect(view().name("word/list"))
                .andExpect(model().attributeExists("words"));
    }

    @Test
    @DisplayName("Test that GET  /words/find  works correctly")
    void getFindWord() throws Exception {
        this.mockMvc
                .perform(get("/words/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("word/find"))
                .andExpect(model().attributeExists("searchPerformed"));
    }

    @Test
    @DisplayName("Test that POST  /words/find  works correctly")
    void postFindWord() throws Exception {
        Tag testTag = new Tag();
        testTag.setName("tag1");
        tagService.create(testTag);
        Word testWord = new Word();
        testWord.setForeignWord("test");
        testWord.setTranslationWord("тест");
        testWord.setDescription("testing process");
        testWord.setTag(testTag);
        Word expected = wordService.create(testWord);
        this.mockMvc
                .perform(post("/words/find")
                        .param("wordPart", "test"))
                .andExpect(status().isOk())
                .andExpect(view().name("word/find"))
                .andExpect(model().attributeExists("searchPerformed"))
                .andExpect(model().attributeExists("words"))
                .andExpect(model().attributeExists("word"));


//        assertEquals()
    }

    @Test
    @DisplayName("Test that GET  /words/create  works correctly")
    void getCreatedWord() throws Exception {
        this.mockMvc
                .perform(get("/words/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("word/create"))
                .andExpect(model().attributeExists("tags"))
                .andExpect(model().attributeExists("wordRequest"));
    }

    @Test
    @DisplayName("Test that POST  /words/create  works correctly")
    void postCreateWord() throws Exception {
        this.mockMvc
                .perform(post("/words/create")
                        .param("wordRequest", "wordRequest"))
                .andExpect(status().isOk())
                .andExpect(view().name("word/create"))
                .andExpect(model().attributeExists("tags"))
                .andExpect(model().attributeExists("errors"));
    }

    @Test
    void getDeleteWord() {
    }

    @Test
    void getUpdateWord() {
    }

    @Test
    void postUpdateWord() {
    }
}