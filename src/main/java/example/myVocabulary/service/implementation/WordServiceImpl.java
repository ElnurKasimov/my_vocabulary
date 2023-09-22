package example.myVocabulary.service.implementation;

import example.myVocabulary.model.Word;
import example.myVocabulary.service.WordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordServiceImpl implements WordService {

    @Override
    public Word create(Word Word) {
        return null;
    }

    @Override
    public Word readById(long id) {
        return null;
    }

    @Override
    public Word update(Word Word) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<Word> getAll() {
        return null;
    }

    @Override
    public Word readByForeignWord(String foreignWord) {
        return null;
    }

    @Override
    public Word readByTranslation(String translation) {
        return null;
    }

    @Override
    public Word readByWordPart(String wordPart) {
        return null;
    }


}
