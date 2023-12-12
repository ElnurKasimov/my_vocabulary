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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
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
                    .toList();
            model.addAttribute("tags",tags);
            return "word/create";
        }
        Word newWord = wordTransformer.toEntity(wordRequest);
        wordService.create(newWord);
        Tag tag = tagService.readByName(wordRequest.getTagName());
        tag.getWords().add(newWord);
        model.addAttribute("tag", tag);
        model.addAttribute("words",tag.getWords());
        List<TagResponse> tags = tagService.getAll().stream()
                .map(tagTransformer::fromEntity)
                .toList();
        model.addAttribute("tags",tags);
        model.addAttribute("wordRequest", new WordRequest());
        return "word/create";
    }

    @GetMapping(value = {"/{id}/delete"})
    public String getDeleteWord(@PathVariable (name = "id") long id, Model model) {
        Word wordToDelete = wordService.readById(id);
        long tagId = wordToDelete.getTag().getId();
        wordService.delete(id);
        Tag tag = tagService.readById(tagId);
        tag.getWords().remove(wordToDelete);
        model.addAttribute("words",
                        tag.getWords().stream()
                        .map(wordTransformer::fromEntityForCRUD)
                        .collect(Collectors.toList()));
        model.addAttribute("tagName", tag.getName());
        return "tag/tag-words";
    }


    @GetMapping(value = {"/{id}/update"})
    public String getUpdateWord(@PathVariable (name = "id") long id, Model model) {
        List<TagResponse> tags = tagService.getAll().stream()
                .map(tagTransformer::fromEntity)
                .toList();
        model.addAttribute("tags",tags);
        model.addAttribute("word", wordService.readById(id));
        // TODO refactor template - add hidden field id and return it for POST handling
        return "word/update";
    }

    @PostMapping(value = {"/{id}/update"})
    public String postUpdateWord(@PathVariable (name = "id") long id,
                                @ModelAttribute("wordRequest") @Valid WordRequest wordRequest,
                                BindingResult bindingResult, Model model) {
        return "tag/tag-words";
    }


}
