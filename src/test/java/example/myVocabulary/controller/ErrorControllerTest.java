package example.myVocabulary.controller;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ErrorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest(name = "{index} - GET /{0} works correctly")
    @CsvSource({"404", "500"})
    void getError(String endpoint) throws Exception {
     mockMvc.perform(get("/" + endpoint))
                .andExpect(status().isOk())
                .andExpect(view().name(endpoint));
    }
}