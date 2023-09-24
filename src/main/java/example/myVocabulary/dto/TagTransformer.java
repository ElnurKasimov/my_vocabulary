package example.myVocabulary.dto;

import com.softserve.itacademy.todolist.model.User;
import com.softserve.itacademy.todolist.repository.ToDoRepository;
import example.myVocabulary.model.Tag;
import example.myVocabulary.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TagTransformer {
    private final TagRepository tagRepository;

    public Tag toEntity(TagRequest tagRequest) {
        Tag tag = new Tag();
        tag.setName(tagRequest.getName());
        return tag;
    }

    public  TagResponse fromEntity(Tag tag) {
        return new TagResponse(tag);
    }

}
