package example.myVocabulary.controller;

import example.myVocabulary.dto.*;
import example.myVocabulary.model.Tag;
import example.myVocabulary.model.Word;
import example.myVocabulary.service.TagService;
import example.myVocabulary.service.WordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/words")
@RequiredArgsConstructor
public class WordController {
    private final TagService tagService;
    private final TagTransformer tagTransformer;
    private final WordService wordService;
    private final WordTransformer wordTransformer;

    @GetMapping(value = {"/"})
    public String getAllWords(Model model) {
        model.addAttribute("words",
                wordService.getAll().stream()
                        .map(wordTransformer::fromEntityForCRUD)
                        .collect(Collectors.toList()));
        return "word/list";
    }

    @GetMapping(value = {"/find"})
    public String getFindWord(Model model) {
        boolean searchPerformed = false;
        model.addAttribute("searchPerformed", searchPerformed);
        return "word/find";
    }
    @PostMapping(value = {"/find"})
    public String postFindWord(@RequestParam("wordPart") String wordPart, Model model) {
        boolean searchPerformed =  true;
        model.addAttribute("words",
                new ArrayList<>(wordService.readByWordPart(wordPart)));
        model.addAttribute("word", wordPart);
        model.addAttribute("searchPerformed", searchPerformed);
        return "word/find";
    }

    @GetMapping(value = {"/create"})
    public String getCreatedWord(Model model) {
        List<TagResponse> tags = tagService.getAll().stream()
                        .map(tagTransformer::fromEntity)
                        .sorted()
                        .toList();
        model.addAttribute("tags",tags);
        model.addAttribute("wordRequest", new WordRequest());
        return "word/create";
    }
    @PostMapping(value = {"/create"})
    public String postCreateWord(@ModelAttribute("wordRequest") @Valid WordRequest wordRequest,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            List<TagResponse> tags = tagService.getAll().stream()
                    .map(tagTransformer::fromEntity)
                    .sorted()
                    .toList();
            model.addAttribute("tags",tags);
            return "word/create";
        }
        Word newWord = wordTransformer.toEntity(wordRequest);
        wordService.create(newWord);
        Tag tag = tagService.readByName(wordRequest.getTagName());
        model.addAttribute("tag", tag);
        List<TagResponse> tags = tagService.getAll().stream()
                .map(tagTransformer::fromEntity)
                .sorted()
                .toList();
//        List<TagResponse> sortedTags = new ArrayList<>(tags);
//        Collections.sort(sortedTags);
        model.addAttribute("tags",tags);
        model.addAttribute("wordRequest", new WordRequest());
        model.addAttribute("scrollToBottom", true);
        return "word/create";
    }

    @GetMapping(value = {"/{id}/delete"})
    public String getDeleteWord(@PathVariable (name = "id") long id, Model model) {
        Word wordToDelete = wordService.readById(id);
        long tagId = wordToDelete.getTag().getId();
        wordService.delete(id);
        Tag tag = tagService.readById(tagId);
        model.addAttribute("words", tag.getWords().stream().sorted().toList());
        model.addAttribute("tagName", tag.getName());
        return "tag/tag-words";
    }

    @GetMapping(value = {"/{id}/update"})
    public String getUpdateWord(@PathVariable (name = "id") long id, Model model) {
        model.addAttribute("tags",tagService.getAll().stream().sorted().toList());
        model.addAttribute("word", wordService.readById(id));
        return "word/update";
    }

    @PostMapping(value = {"/{id}/update"})
    public String postUpdateWord(@PathVariable (name = "id") long id,
                                @ModelAttribute("wordRequest") @Valid WordRequest wordRequest,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("tags",tagService.getAll().stream().sorted().toList());
            return "word/update";
        }
        Word toUpdate = wordTransformer.toEntity(wordRequest);
        toUpdate.setId(id);
        Word updatedWord = wordService.update(toUpdate);
        model.addAttribute("tag",updatedWord.getTag());
        model.addAttribute("tagName", updatedWord.getTag().getName());
        model.addAttribute("words", updatedWord.getTag().getWords().stream()
                        .map(wordTransformer::fromEntityForCRUD)
                        .sorted().toList());
        return "tag/tag-words";
    }
}
