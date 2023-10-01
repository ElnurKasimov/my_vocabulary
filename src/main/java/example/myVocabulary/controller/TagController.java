package example.myVocabulary.controller;

import example.myVocabulary.dto.TagTransformer;
import example.myVocabulary.dto.WordTransformer;
import example.myVocabulary.service.TagService;
import example.myVocabulary.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;
    private final TagTransformer tagTransformer;
    private final WordService wordService;
    private final WordTransformer wordTransformer;

    @GetMapping(value = {"/"})
    public String getAllTags(Model model) {
        model.addAttribute("tags",
                tagService.getAll().stream()
                        .map(tagTransformer::fromEntity)
                        .collect(Collectors.toList()));
        return "tag/tag-list";
    }

    @GetMapping(value = {"/{id}"})
    public String getAllTags(@PathVariable (name = "id") long id,  Model model) {
        model.addAttribute("words",
                tagService.readById(id).getWords().stream()
                        .map(wordTransformer::fromEntityForCRUD)
                        .collect(Collectors.toList()));
        model.addAttribute("tagName", tagService.readById(id).getName());
        return "tag/tag-words";
    }

}
