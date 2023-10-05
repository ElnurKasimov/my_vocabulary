package example.myVocabulary.dto;

import example.myVocabulary.model.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class TagResponse {
    private long id;
    private String name;

    public TagResponse(Tag tag) {
        id = tag.getId();
        name = tag.getName();
    }
}
