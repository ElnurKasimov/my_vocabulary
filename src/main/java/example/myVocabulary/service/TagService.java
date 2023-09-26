package example.myVocabulary.service;

import example.myVocabulary.model.Tag;
import java.util.List;

public interface TagService {
    Tag create(Tag Tag);
    Tag readById(long id);
    Tag update(Tag Tag);
    void delete(long id);
    List<Tag> getAll();
    Tag readByName(String name);

    List<Tag> getTenRandom();


}
