package example.myVocabulary.dto;

import example.myVocabulary.model.Tag;
import example.myVocabulary.model.Word;
import lombok.Getter;

import java.util.Objects;


@Getter
public class WordResponseForCRUD implements Comparable<WordResponseForCRUD> {
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

    public int compareTo(WordResponseForCRUD other) {
        return this.foreignWord.compareTo(other.foreignWord);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordResponseForCRUD that = (WordResponseForCRUD) o;
        return id == that.id && Objects.equals(foreignWord, that.foreignWord) && Objects.equals(translationWord, that.translationWord) && Objects.equals(description, that.description) && Objects.equals(tagName, that.tagName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, foreignWord, translationWord, description, tagName);
    }
}
