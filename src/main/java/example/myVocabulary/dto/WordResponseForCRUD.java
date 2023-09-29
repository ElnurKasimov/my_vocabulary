package example.myVocabulary.dto;

import example.myVocabulary.model.Tag;
import example.myVocabulary.model.Word;
import lombok.Getter;


@Getter
public class WordResponseForCRUD {
    private long id;
    private String foreignWord;
    private String translationWord;
    private String description;
    private String tagName;

    public WordResponseForCRUD(Word word) {
        id = word.getId();
        foreignWord = word.getForeignWord();
        translationWord = word.getTranslationWord();
        description = word.getDescription();
        tagName = word.getTag().getName();
    }



}
