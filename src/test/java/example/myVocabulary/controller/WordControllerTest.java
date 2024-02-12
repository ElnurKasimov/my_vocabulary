package example.myVocabulary.controller;

import example.myVocabulary.dto.WordTransformer;
import example.myVocabulary.model.Tag;
import example.myVocabulary.model.Word;
import example.myVocabulary.service.TagService;
import example.myVocabulary.service.WordService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WordControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EntityManager entityManager;

    @MockBean
    private WordService wordService;

    @MockBean
    private TagService tagService;

//    @Autowired
//    private WordTransformer wordTransformer;

    @Test
    @DisplayName("Test that GET  /words/  works correctly")
    void getAllWords() throws Exception {
        Tag mockTag1 = new Tag(1L, "tag1", new ArrayList<>());
        Tag mockTag2 = new Tag(2L, "tag2", new ArrayList<>());
        Word mockWord1 = new Word(1L, "word1", "слово1", "", mockTag1);
        Word mockWord2 = new Word(2L, "word2", "слово2", "", mockTag1);
        Word mockWord3 = new Word(3L, "word3", "слово3", "", mockTag2);
        Word mockWord4 = new Word(4L, "word4", "слово4", "", mockTag2);
        List<Word> expected = new ArrayList<>();
        expected.add(mockWord1);
        expected.add(mockWord2);
        expected.add(mockWord3);
        expected.add(mockWord4);
        when(wordService.getAll()).thenReturn(expected);
        this.mockMvc
                .perform(get("/words/"))
                .andExpect(status().isOk())
                .andExpect(view().name("word/list"))
                .andExpect(model().attributeExists("words"))
                .andExpect(model().attribute("words", hasSize(4)));
//                .andExpect(model().attribute("words", contains(wordTransformer.fromEntityForCRUD(mockWord3))));
        verify(wordService, times(1)).getAll();
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
    @DisplayName("Test that POST  /words/find  works correctly and finds among foreign words")
    void postFindWordAmongForeign() throws Exception {
        Tag mockTag = new Tag();
        mockTag.setId(1L);
        mockTag.setName("tag1");
        Word mockWord = new Word();
        mockWord.setId(1L);
        mockWord.setForeignWord("test");
        mockWord.setTranslationWord("тест");
        mockWord.setDescription("testing process");
        mockWord.setTag(mockTag);
        List<Word> expected = new ArrayList<>();
        expected.add(mockWord);
        when(wordService.readByWordPart("es")).thenReturn(expected);
        this.mockMvc
                .perform(post("/words/find")
                        .param("wordPart", "es"))
                .andExpect(status().isOk())
                .andExpect(view().name("word/find"))
                .andExpect(model().attributeExists("searchPerformed"))
                .andExpect(model().attributeExists("words"))
                .andExpect(model().attributeExists("word"))
                .andExpect(model().attribute("words", hasSize(1)))
                .andExpect(model().attribute("words", contains(mockWord)));
        verify(wordService, times(1)).readByWordPart("es");
    }

    @Test
    @DisplayName("Test that POST  /words/find  works correctly and finds among translations")
    void postFindWordAmongTranslations() throws Exception {
        Tag mockTag = new Tag();
        mockTag.setId(1L);
        mockTag.setName("tag1");
        Word mockWord = new Word();
        mockWord.setId(1L);
        mockWord.setForeignWord("test");
        mockWord.setTranslationWord("тест");
        mockWord.setDescription("testing process");
        mockWord.setTag(mockTag);
        List<Word> expected = new ArrayList<>();
        expected.add(mockWord);
        when(wordService.readByWordPart("ест")).thenReturn(expected);
        this.mockMvc
                .perform(post("/words/find")
                        .param("wordPart", "ест"))
                .andExpect(status().isOk())
                .andExpect(view().name("word/find"))
                .andExpect(model().attributeExists("searchPerformed"))
                .andExpect(model().attributeExists("words"))
                .andExpect(model().attributeExists("word"))
                .andExpect(model().attribute("words", hasSize(1)))
                .andExpect(model().attribute("words", contains(mockWord)));
        verify(wordService, times(1)).readByWordPart("ест");
    }


    //TODO  cover 'create' with mocks
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

//    @Test
//    @DisplayName("Test that GET  /words/{id}/delete works correctly")
//    void getDeleteWord() throws Exception {
//        this.mockMvc
//                .perform(get("/words/{id}/create")
//                        .param("wordRequest", "wordRequest"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("word/create"))
//                .andExpect(model().attributeExists("tags"))
//                .andExpect(model().attributeExists("errors"));
//    }

    @Test
    void getUpdateWord() {
    }

    @Test
    void postUpdateWord() {
    }
}