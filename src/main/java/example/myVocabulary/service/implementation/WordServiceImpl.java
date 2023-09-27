package example.myVocabulary.service.implementation;

import example.myVocabulary.model.Word;
import example.myVocabulary.repository.WordRepository;
import example.myVocabulary.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {
    private  final WordRepository wordRepository;
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
    public List<Word> getTenRandom() {
        List<Word> result = wordRepository.findTenRandom();
        return result;
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
