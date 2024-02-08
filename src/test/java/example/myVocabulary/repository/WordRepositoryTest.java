package example.myVocabulary.repository;

import example.myVocabulary.model.Tag;
import example.myVocabulary.model.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class WordRepositoryTest {
    @Autowired
    WordRepository wordRepository;

    @Test
    @DisplayName("Test that findByWordPart() works correctly when searched in foreign words")
    void findByWordPartFromForeign() {
        // there is starting population in migrations
        List<Word> actual = wordRepository.findByWordPart("ast");
        assertEquals(1,actual.size());
        assertEquals("кидать, бросать", actual.get(0).getTranslationWord());

    }

    @Test
    @DisplayName("Test that findByWordPart() works correctly when searched in translation words")
    void findByWordPartFromTranslation() {
        // there is starting population in migrations
        List<Word> actual = wordRepository.findByWordPart("по");
        assertEquals(6,actual.size());
        assertEquals("consistent", actual.get(0).getForeignWord());
        assertEquals("to urge", actual.get(1).getForeignWord());
        assertEquals("descending", actual.get(2).getForeignWord());
        assertEquals("agile", actual.get(5).getForeignWord());
    }

    @Test
    @DisplayName("Test that findTenRandom() retrieves 10 different words")
    void findTenRandom() {
        // there is starting population in migrations
        List<Word> actual = wordRepository.findTenRandom();
        assertEquals(10, actual.size()); //retrieve 10?
        Set<Long> ids = new HashSet<>();
        for (Word word: actual) {
            ids.add(word.getId());
        }
        assertEquals(10, ids.size()); //all 10 is different?
    }
}