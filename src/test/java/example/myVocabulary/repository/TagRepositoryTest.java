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
class TagRepositoryTest {
    @Autowired
    TagRepository tagRepository;

    @Test
    @DisplayName("Test that findByName() works correctly")
    void findByName() {
        // there is starting population in migrations
        assertEquals(6, tagRepository.findByName("IT").get().getId());
    }

    @Test
    @DisplayName("Test that findTenRandom() retrieves 10 different tags")
    void findTenRandom() {
        // there is starting population in migrations
        Tag ninth = new Tag();
        Tag tenth = new Tag();
        ninth.setName("ninth");
        tenth.setName("tenth");
        tagRepository.save(ninth);
        tagRepository.save(tenth);
        List<Tag> actual = tagRepository.findTenRandom();
        assertEquals(10, actual.size()); //retrieve 10?
        Set<Long> ids = new HashSet<>();
        for (Tag tag: actual) {
            ids.add(tag.getId());
        }
        assertEquals(10,  ids.size());//all tags are different?
    }

    @Test
    @DisplayName("Test that findTagById() works correctly")
    void findTagById() {
        // there is starting population in migrations
        assertEquals("Activities", tagRepository.findTagById(5).get().getName());
    }
}