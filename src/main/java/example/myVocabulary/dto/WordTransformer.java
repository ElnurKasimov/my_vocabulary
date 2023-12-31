package example.myVocabulary.dto;

import example.myVocabulary.model.Tag;
import example.myVocabulary.model.Word;
import example.myVocabulary.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WordTransformer {
    private final TagService tagService;

    public Word toEntity(WordRequest wordRequest) {
        Word word = new Word();
        word.setForeignWord(wordRequest.getForeignWord());
        word.setTranslationWord(wordRequest.getTranslationWord());
        word.setDescription(wordRequest.getDescription());
        word.setTag(tagService.readByName(wordRequest.getTagName()));
        return word;
    }

    public WordRequest toWordRequest(Word word) {
        WordRequest wordRequest = new WordRequest();
        wordRequest.setForeignWord(word.getForeignWord());
        wordRequest.setTranslationWord(word.getTranslationWord());
        wordRequest.setDescription(word.getDescription());
        wordRequest.setTagName(word.getTag().getName());
    return wordRequest;
    }

    public  WordResponseForHome fromEntityForHome(Word word) {
        return new WordResponseForHome(word);
    }

    public  WordResponseForCRUD fromEntityForCRUD(Word word) {
        return new WordResponseForCRUD(word);
    }

}
