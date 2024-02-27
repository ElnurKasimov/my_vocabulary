package example.myVocabulary.controller;

import example.myVocabulary.service.TagService;
import example.myVocabulary.service.WordService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    WordService wordService;

    @MockBean
    TagService tagService;

    @ParameterizedTest(name = "{index} test that GET {0} works correct")
    @CsvSource({"/", "/home"})
        void getHome(String endpoint) throws Exception {
        mockMvc.perform(get(endpoint))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attributeExists("tags", "words"));
        verify(wordService,times(1)).getTenRandom();
        verify(tagService,times(1)).getTenRandom();
    }

}