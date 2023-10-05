package example.myVocabulary.service;

import example.myVocabulary.dto.TagRequest;
import example.myVocabulary.model.Tag;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface TagService {
    Tag create(Tag tag);
    Tag readById(long id);
    Tag update(Tag tag);
    void delete(long id);
    List<Tag> getAll();
    Tag readByName(String name);

    List<Tag> getTenRandom();

    List<String> getTagErrors(String name, BindingResult bindingResult);


}
