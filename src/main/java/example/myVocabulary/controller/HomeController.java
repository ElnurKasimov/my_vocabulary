package example.myVocabulary.controller;

import example.myVocabulary.dto.TagTransformer;
import example.myVocabulary.dto.WordResponseForHome;
import example.myVocabulary.dto.WordTransformer;
import example.myVocabulary.service.TagService;
import example.myVocabulary.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final TagService tagService;
    private final TagTransformer tagTransformer;
    private final WordService wordService;
    private final WordTransformer wordTransformer;

    @GetMapping(value = {"/home", "/"})
    public String getHome(Model model) {
        model.addAttribute("tags",
                tagService.getTenRandom().stream()
                        .map(tagTransformer::fromEntity)
                        .collect(Collectors.toList()));

        model.addAttribute("words",
                wordService.getTenRandom().stream()
                        .map(wordTransformer::fromEntityForHome)
                        .collect(Collectors.toList()));
        return "home";
    }

}
