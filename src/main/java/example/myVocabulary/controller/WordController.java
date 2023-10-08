package example.myVocabulary.controller;

import example.myVocabulary.dto.TagResponse;
import example.myVocabulary.dto.TagTransformer;
import example.myVocabulary.dto.WordRequest;
import example.myVocabulary.dto.WordTransformer;
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
                wordService.readByWordPart(wordPart).stream()
                        .map(wordTransformer::fromEntityForCRUD)
                        .collect(Collectors.toList()));
        model.addAttribute("word", wordPart);
        model.addAttribute("searchPerformed", searchPerformed);
        return "word/find";
    }

    @GetMapping(value = {"/create"})
    public String getCreatedWord(
//            @ModelAttribute("tag") Object tag,
//            @ModelAttribute("words") Object words,
                                 Model model) {
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
                                 Model model
//                                 RedirectAttributes redirectAttributes
    ) {


        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            List<TagResponse> tags = tagService.getAll().stream()
                    .map(tagTransformer::fromEntity)
                    .toList();
            model.addAttribute("tags",tags);
            return "word/create";
        }
        Word newWord = wordService.create(wordTransformer.toEntity(wordRequest));
        Tag tag = tagService.readByName(wordRequest.getTagName());
//        redirectAttributes.addFlashAttribute("tag", tag);
//        redirectAttributes.addFlashAttribute("words", tag.getWords());
        model.addAttribute("tag", tag);
        model.addAttribute("words",tag.getWords());
        return "word/create";
    }

}
