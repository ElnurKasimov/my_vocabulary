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

    public  WordResponseForHome fromEntityForHome(Word word) {
        return new WordResponseForHome(word);
    }

}
