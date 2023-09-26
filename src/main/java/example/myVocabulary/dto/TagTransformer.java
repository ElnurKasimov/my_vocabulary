package example.myVocabulary.dto;

import example.myVocabulary.model.Tag;
import example.myVocabulary.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class TagTransformer {

    public Tag toEntity(TagRequest tagRequest) {
        Tag tag = new Tag();
        tag.setName(tagRequest.getName());
        return tag;
    }

    public  TagResponse fromEntity(Tag tag) {
        return new TagResponse(tag);
    }

}
