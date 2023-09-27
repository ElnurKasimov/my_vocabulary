package example.myVocabulary.dto;

import example.myVocabulary.model.Tag;
import example.myVocabulary.model.Word;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class WordResponseForHome {
    private String foreignWord;
    private String translationWord;
    private String tagName;

    public WordResponseForHome(Word word) {
        foreignWord = word.getForeignWord();
        translationWord = word.getTranslationWord();
        tagName = word.getTag().getName();
    }
}
