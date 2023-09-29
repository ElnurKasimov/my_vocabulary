package example.myVocabulary.controller;

import example.myVocabulary.dto.TagTransformer;
import example.myVocabulary.dto.WordTransformer;
import example.myVocabulary.service.TagService;
import example.myVocabulary.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

}
