package example.myVocabulary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

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
}
