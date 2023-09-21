package example.myVocabulary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class WordRequest {
    @NotNull(message = "The 'word' cannot be null")
    @NotBlank(message = "The 'word' cannot be empty")
    private String foreignWord;

    @NotNull(message = "The 'translation' cannot be null")
    @NotBlank(message = "The 'translation' cannot be empty")
    private String translationWord;

    @NotNull(message = "The 'tag' cannot be null")
    @NotBlank(message = "The 'tag' cannot be empty")
    private String tag;
}
