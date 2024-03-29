package example.myVocabulary.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name="words")
@NoArgsConstructor
@AllArgsConstructor
public class Word implements Comparable<Word> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name="foreign_word")
    @NotNull(message = "The 'word' cannot be null")
    private String foreignWord;

    @Column (name="translation_word")
    @NotNull(message = "The 'translation' cannot be null")
    private String translationWord;

    @Column (name="description")
    private String description;

    @NotNull(message = "The 'tag' cannot be null")
    @ManyToOne
    @JoinColumn(name="tag_id")
    private Tag tag;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getForeignWord() {
        return foreignWord;
    }

    public void setForeignWord(String foreignWord) {
        this.foreignWord = foreignWord;
    }

    public String getTranslationWord() {
        return translationWord;
    }

    public void setTranslationWord(String translationWord) {
        this.translationWord = translationWord;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return id == word.id && Objects.equals(foreignWord, word.foreignWord) && Objects.equals(translationWord, word.translationWord) && Objects.equals(description, word.description) && Objects.equals(tag, word.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, foreignWord, translationWord, description, tag);
    }

    @Override
    public int compareTo(Word other) {
        return this.foreignWord.compareTo(other.foreignWord);
    }
}
