package example.myVocabulary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class WordRequest {
    @NotNull(message = "The 'word' cannot be null")
    @NotBlank(message = "The 'word' cannot be empty")
    @Size(max = 255, message = "The 'word' length shouldn't be more than 255 symbols")
    private String foreignWord;

    @NotNull(message = "The 'translation' cannot be null")
    @NotBlank(message = "The 'translation' cannot be empty")
    @Size(max = 255, message = "The 'translation' length shouldn't be more than 255 symbols")
    private String translationWord;

    @Size(max = 1000, message = "The length shouldn't be more than 1000 symbols")
    private String description;

    @NotNull(message = "The 'tag' cannot be null")
    @NotBlank(message = "The 'tag' cannot be empty")
    private String tagName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordRequest that = (WordRequest) o;
        return Objects.equals(foreignWord, that.foreignWord) && Objects.equals(translationWord, that.translationWord) && Objects.equals(description, that.description) && Objects.equals(tagName, that.tagName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foreignWord, translationWord, description, tagName);
    }

}
