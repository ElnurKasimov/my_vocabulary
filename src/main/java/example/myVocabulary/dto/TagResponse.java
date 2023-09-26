package example.myVocabulary.dto;

import example.myVocabulary.model.Tag;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class TagResponse {
    private long id;
    private String name;

    public TagResponse(Tag tag) {
        id = tag.getId();
        name = tag.getName();
    }
}
