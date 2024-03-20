package example.myVocabulary.controller;

import example.myVocabulary.dto.TagResponse;
import example.myVocabulary.dto.TagTransformer;
import example.myVocabulary.dto.WordResponseForCRUD;
import example.myVocabulary.dto.WordTransformer;
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
import java.util.Arrays;
import java.util.Collections;
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

    @Autowired
    private WordTransformer wordTransformer;

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
    @DisplayName("Test that GET  /practice/{id}  works correctly")
    void getTagForPractice() throws Exception {
        Tag mockTag1 = new Tag(1L, "mockTag1", new ArrayList<>());
        Tag mockTag2 = new Tag(2L, "mockTag2", new ArrayList<>());
        Word mockWord1 = new Word(1L, "word1", "слово1", "", mockTag1);
        Word mockWord2 = new Word(2L, "word2", "слово2", "", mockTag1);
        Word mockWord3 = new Word(3L, "word3", "слово3", "", mockTag2);
        Word mockWord4 = new Word(4L, "word4", "слово4", "", mockTag2);
        List<Tag> tags = new ArrayList<>();
        tags.add(mockTag1);
        tags.add(mockTag2);
        List<Word> words1 = new ArrayList<>();
        words1.add(mockWord1);
        words1.add(mockWord2);
        List<Word> words2 = new ArrayList<>();
        words2.add(mockWord3);
        words2.add(mockWord4);
        mockTag1.setWords(words1);
        mockTag2.setWords(words2);


        when(tagService.readById(1L)).thenReturn(mockTag1);
        when(tagService.getAll()).thenReturn(tags);

        List<WordResponseForCRUD> words = mockTag1.getWords().stream()
                .map(wordTransformer::fromEntityForCRUD)
                .toList();

        int rowNumber = 1;
        boolean[] foreign = {true, true};
        boolean[] translation = {true, true};
        boolean[] description = {true, true};
        List<WordResponseForCRUD> toPractice = new ArrayList<>(words);
        Collections.shuffle(toPractice);


        mockMvc.perform(get("/practice/{id}", 1L)
                        .flashAttr("translateDirection","translateDirection")
                        .param("rowNumber", String.valueOf(rowNumber))
                        .param("foreign", Arrays.toString(foreign))
                        .param("translation", Arrays.toString(translation))
                        .param("description", Arrays.toString(description)) )

                .andExpect(status().isOk())
                .andExpect(view().name("practice-tag"))
                .andExpect(model().attributeExists("words", "tag", "foreign", "translation", "description", "tags"))
                .andExpect(model().attribute("translateDirection", "translateDirection"))
                ;
        verify(tagService, times(1)).readById(1L);
        verify(tagService, times(1)).getAll();

    }

    @Test
    void postTagForPractice() {
    }
}