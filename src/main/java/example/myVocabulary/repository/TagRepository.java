package example.myVocabulary.repository;

import example.myVocabulary.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    public Tag findByName(String name);

    @Query(value = "SELECT * FROM tags ORDER BY RAND() LIMIT 10", nativeQuery = true)
    public List<Tag> findTenRandom();


}
