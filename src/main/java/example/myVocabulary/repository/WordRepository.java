package example.myVocabulary.repository;

import example.myVocabulary.model.Tag;
import example.myVocabulary.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WordRepository extends JpaRepository<Word, Long> {
    @Query("SELECT w FROM Word w WHERE LOWER(w.foreignWord) LIKE %:wordPart% OR LOWER(w.translationWord) LIKE %:wordPart%")
    public List<Word> findByWordPart(@Param("wordPart") String wordPart);
    @Query(value = "SELECT *  FROM words ORDER BY RAND() LIMIT 10", nativeQuery = true)
    public List<Word> findTenRandom();
}
