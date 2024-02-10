package example.myVocabulary.service.implementation;

import example.myVocabulary.exception.EntityNotFoundException;
import example.myVocabulary.exception.NullEntityReferenceException;
import example.myVocabulary.model.Tag;
import example.myVocabulary.model.Word;
import example.myVocabulary.repository.WordRepository;
import example.myVocabulary.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {
    private  final WordRepository wordRepository;
    @Override
    public Word create(Word word) {
        if(word == null)
            throw new NullEntityReferenceException("Cannot create empty word object");
        try{
            return wordRepository.save(word);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Word readById(long id) {
        Optional<Word> optional = wordRepository.findById(id);
        if(optional.isEmpty())
            throw new EntityNotFoundException("Word with id: " + id + " does not exist");
        return optional.get();
    }

    @Override
    public Word update(Word word) {
        if(word == null)
            throw new NullEntityReferenceException("Cannot update empty word object");
        try{
            Word oldWord = readById(word.getId());
            return wordRepository.save(word);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void delete(long id) {
        try {
            wordRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Word> getAll() {
        return wordRepository.findAll();
    }

    @Override
    public List<Word> getTenRandom() {
        List<Word> result = wordRepository.findTenRandom();
        return result;
    }

    @Override
    public List<Word> readByWordPart(String wordPart) {
        return wordRepository.findByWordPart(wordPart);
    }

}
