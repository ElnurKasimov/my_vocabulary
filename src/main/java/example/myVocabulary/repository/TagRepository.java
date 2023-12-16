package example.myVocabulary.repository;

import example.myVocabulary.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    public Optional<Tag> findByName(String name);

    @Query(value = "SELECT * FROM tags ORDER BY RAND() LIMIT 10", nativeQuery = true)
    public List<Tag> findTenRandom();

    @Query(value = "SELECT t FROM Tag t JOIN FETCH t.words b WHERE t.id = :tagId ")
    public Optional<Tag> findTagById(@Param("tagId") long id);

}
