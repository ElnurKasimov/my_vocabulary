package example.myVocabulary.service.implementation;

import example.myVocabulary.model.Tag;
import example.myVocabulary.model.Word;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class WordServiceImplTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    WordServiceImpl wordService;
    @Autowired
    TagServiceImpl tagService;

    @Test
    @DisplayName("Test that updating word.tag reflects in tag.words")
    @Commit
    void update() {
        // there is starting population in migrations
        List<Word> actualList = wordService.readByWordPart("consist");
        Word actualWord = actualList.get(0);
        Tag newTag = tagService.readByName("IT");
        actualWord.setTag(newTag);
        wordService.update(actualWord);
        entityManager.flush();
        Tag actualTag = tagService.readByName("IT");
        assertTrue(actualTag.getWords().contains(actualWord));
    }

    @Test
    @DisplayName("Test that deleting word reflects in tag.words")
    void delete() {
        // there is starting population in migrations
        List<Word> actualList = wordService.readByWordPart("consist");
        Word actualWord = actualList.get(0);
        wordService.delete(actualWord.getId());
        entityManager.flush();
        Tag actualTag = tagService.readByName("Sales");
        assertEquals(1L, actualTag.getWords().size());
        assertFalse(actualTag.getWords().contains(actualWord));
    }

}