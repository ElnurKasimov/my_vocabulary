package example.myVocabulary.service.implementation;

import example.myVocabulary.model.Tag;
import example.myVocabulary.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Override
    public Tag create(Tag Tag) {
        return null;
    }

    @Override
    public Tag readById(long id) {
        return null;
    }

    @Override
    public Tag update(Tag Tag) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<Tag> getAll() {
        return null;
    }
}
