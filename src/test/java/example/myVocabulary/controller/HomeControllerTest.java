package example.myVocabulary.controller;

import example.myVocabulary.service.TagService;
import example.myVocabulary.service.WordService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class HomeControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    WordService wordService;

    @MockBean
    TagService tagService;

    @Test
    @DisplayName("test that GET /home works correct")
    void getHome() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attributeExists("tags", "words"))   ;

        verify(tagService,times(1)).getTenRandom()
    }

    @Test
    @DisplayName("test that GET / works correct")
    void getHomeSlash() {

    }
}