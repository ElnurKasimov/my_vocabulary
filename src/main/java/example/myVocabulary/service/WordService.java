package example.myVocabulary.service;

import example.myVocabulary.model.Word;

import java.util.List;

public interface WordService {
    Word create(Word Word);
    Word readById(long id);
    Word update(Word Word);
    void delete(long id);
    List<Word> getAll();
    List<Word> getTenRandom();
    List<Word> readByWordPart(String wordPart);
    
}
