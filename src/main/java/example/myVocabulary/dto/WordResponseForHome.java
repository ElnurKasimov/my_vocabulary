package example.myVocabulary.dto;

import example.myVocabulary.model.Tag;
import example.myVocabulary.model.Word;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class WordResponseForHome {
    private String foreignWord;
    private String translationWord;
    private String tagName;

    public WordResponseForHome(Word word) {
        foreignWord = word.getForeignWord();
        translationWord = word.getTranslationWord();
        tagName = word.getTag().getName();
    }

    @Override
    public String toString() {
        return "WordResponseForHome{" +
                "foreignWord='" + foreignWord + '\'' +
                ", translationWord='" + translationWord + '\'' +
                ", tagName='" + tagName + '\'' +
                '}';
    }
}
