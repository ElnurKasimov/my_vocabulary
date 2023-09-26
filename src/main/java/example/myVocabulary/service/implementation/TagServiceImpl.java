package example.myVocabulary.service.implementation;

import example.myVocabulary.model.Tag;
import example.myVocabulary.repository.TagRepository;
import example.myVocabulary.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
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
        return tagRepository.findAll();
    }

    public Tag readByName(String name)  {
        return null;
    }

    @Override
    public List<Tag> getTenRandom() {
        return tagRepository.findTenRandom();
    }
}
