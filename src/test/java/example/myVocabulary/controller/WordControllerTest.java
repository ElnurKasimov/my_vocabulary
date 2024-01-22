package example.myVocabulary.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WordControllerTest {
    @Autowired
    private MockMvc mockMvc;

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
        this.mockMvc
                .perform(post("/words/find")
                        .param("wordPart", "wordPart"))
                .andExpect(status().isOk())
                .andExpect(view().name("word/find"))
                .andExpect(model().attributeExists("searchPerformed"))
                .andExpect(model().attributeExists("words"))
                .andExpect(model().attributeExists("word"));
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