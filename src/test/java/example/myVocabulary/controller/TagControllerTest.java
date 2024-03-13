package example.myVocabulary.controller;

import example.myVocabulary.service.TagService;
import example.myVocabulary.service.WordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

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
    void getAllTags() {
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