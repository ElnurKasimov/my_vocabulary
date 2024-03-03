package example.myVocabulary.controller;

import example.myVocabulary.dto.TagResponse;
import example.myVocabulary.dto.TagTransformer;
import example.myVocabulary.model.Tag;
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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class PracticeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WordService wordService;

    @MockBean
    private TagService tagService;

    @Autowired
    private TagTransformer tagTransformer;

    @Test
    @DisplayName("Test that GET  /practice/  works correctly")
    void getInfoForPractice() throws Exception {
        Tag mockTag1 = new Tag(1L, "mockTag1", new ArrayList<>());
        Tag mockTag2 = new Tag(2L, "mockTag2", new ArrayList<>());
        List<Tag> tags = new ArrayList<>();
        tags.add(mockTag1);
        tags.add(mockTag2);
        when(tagService.getAll()).thenReturn(tags);
        List<TagResponse> mockTags = tags
                .stream()
                .map(tagTransformer::fromEntity)
                .toList();
        mockMvc.perform(get("/practice/"))
                .andExpect(status().isOk())
                .andExpect(view().name("practice-home"))
                .andExpect(model().attributeExists("tags"))
                .andExpect(model().attribute("tags", hasSize(2)))
                .andExpect(model().attribute("tags", equalTo(mockTags)));
        verify(tagService, times(1)).getAll();
    }

    @Test
    @DisplayName("Test that POST  /practice/  works correctly")
    void postInfoForPractice() throws Exception {
        Tag mockTag1 = new Tag(1L, "mockTag1", new ArrayList<>());
        when(tagService.readByName("mockTag1")).thenReturn(mockTag1);
        mockMvc.perform(post("/practice/")
                        .param("tagName", "mockTag1")
                        .param("translateDirection","translateDirection"))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attribute("translateDirection", "translateDirection"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/practice/1"));
        verify(tagService, times(1)).readByName("mockTag1");
    }

    @Test
    void getTagForPractice() {
    }

    @Test
    void postTagForPractice() {
    }
}