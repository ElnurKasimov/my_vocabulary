package example.myVocabulary.repository;

import example.myVocabulary.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WordRepository extends JpaRepository<Word, Long> {
    public Word findByForeignWord(String foreignWord);
    public Word findByTranslationWord(String translation);
    @Query("SELECT w FROM Word w WHERE LOWER(w.foreignWord) LIKE %:wordPart% OR LOWER(w.translationWord) LIKE %:wordPart%")
    public List<Word> findByWordPart(@Param("wordPart") String wordPart);
}
