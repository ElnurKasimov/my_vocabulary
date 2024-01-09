package example.myVocabulary.service.implementation;

import example.myVocabulary.dto.TagRequest;
import example.myVocabulary.exception.EntityNotFoundException;
import example.myVocabulary.exception.InvalidDeletionException;
import example.myVocabulary.exception.NullEntityReferenceException;
import example.myVocabulary.model.Tag;
import example.myVocabulary.repository.TagRepository;
import example.myVocabulary.service.TagService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    @Override
    public Tag create(Tag tag) {
        if(tag == null)
            throw new NullEntityReferenceException("Cannot create empty user object");
        try{
            return tagRepository.save(tag);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Tag readById(long id) {
        Optional<Tag> optional = tagRepository.findTagById(id);
        if(optional.isEmpty())
            throw new EntityNotFoundException("Tag with id: " + id + " does not exist");
        return optional.get();
    }

    @Override
    public Tag update(Tag tag) {
        if(tag == null)
            throw new NullEntityReferenceException("Cannot update empty tag object");
        try{
            return tagRepository.save(tag);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void delete(long id) {
        Tag tagFromBD = tagRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Tag with id: " + id + " does not exist"));
        if (!tagFromBD.getWords().isEmpty()) {
            throw new InvalidDeletionException("It's impossible to remove the tag if there is at least one word with such a tag.\n" +
                    "Please remove all words from the tag or replace them into another one.");
        }
        try {
            tagRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Tag> getAll() {
        return tagRepository.findAll();
    }

    public Tag readByName(String name)  {
        if (name == null) {
            throw new NullEntityReferenceException("Cannot find tag when name equals null");
        }
        Optional<Tag> optional = tagRepository.findByName(name);
        if(optional.isEmpty())
            throw new EntityNotFoundException("Tag with name: " + name + " does not exist");
        return optional.get();
    }

    @Override
    public List<Tag> getTenRandom() {
        return tagRepository.findTenRandom();
    }

    @Override
    public List<String> getTagErrors(String name, BindingResult bindingResult) {
        List<String> errors = new ArrayList<>();
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                errors.add(error.getDefaultMessage());
            }
        }
        if (tagRepository.findByName(name).isPresent()) {
            errors.add("Tag with name '" + name + "' exists already");
        }
        return errors;
    }
}
