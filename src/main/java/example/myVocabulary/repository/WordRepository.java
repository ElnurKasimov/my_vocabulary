package example.myVocabulary.repository;

import example.myVocabulary.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long> {
    public Word findByForeignWord(String foreignWord);
    public Word findByTranslation(String translation);
    public Word findByWordPart(String wordPart);
}
