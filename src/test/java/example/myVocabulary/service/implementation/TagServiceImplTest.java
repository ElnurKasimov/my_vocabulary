package example.myVocabulary.service.implementation;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TagServiceImplTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    WordServiceImpl wordService;
    @Autowired
    TagServiceImpl tagService;
    @Test
    @DisplayName("Test that checked invalid data during creation tag")
    void getTagErrors() {

    }
}