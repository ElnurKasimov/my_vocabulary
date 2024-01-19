package example.myVocabulary.controller;

import example.myVocabulary.dto.TagRequest;
import example.myVocabulary.dto.TagTransformer;
import example.myVocabulary.dto.WordResponseForCRUD;
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


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/practice")
@RequiredArgsConstructor
public class PracticeController {
    private final TagService tagService;
    private final TagTransformer tagTransformer;
    private final WordService wordService;
    private final WordTransformer wordTransformer;

    @GetMapping(value = {"/"})
    public String getInfoForPractice(Model model) {
        model.addAttribute("tags",
        tagService.getAll( ).stream()
                .map(tagTransformer::fromEntity)
                .collect(Collectors.toList()));
    return "practice-home";
    }

    @PostMapping(value = {"/"})
    public String postInfoForPractice(@RequestParam(name="tagName") String tagName,
                                      @RequestParam(name="translateDirection") String translateDirection,
                                      Model model,
                                      RedirectAttributes redirectAttributes) {
        Tag tag = tagService.readByName(tagName);
        redirectAttributes.addFlashAttribute("translateDirection", translateDirection);
        return "redirect:/practice/" + tag.getId();
    }

    @GetMapping(value = {"/{id}"})
    public String getTagForPractice(@PathVariable (name = "id") long id,
                                    @ModelAttribute(name = "translateDirection") String translateDirection,
                                    RedirectAttributes redirectAttributes,
                                    Model model) {
        Tag tag = tagService.readById(id);
        List<WordResponseForCRUD> words = tag.getWords().stream()
                .map(wordTransformer::fromEntityForCRUD)
                .toList();
        List<WordResponseForCRUD> toPractice = new ArrayList<>(words);
        Collections.shuffle(toPractice);
        model.addAttribute("words", toPractice);
        model.addAttribute("tag", tag);
        boolean[] foreign = new boolean[words.size()];
        boolean[] translation = new boolean[words.size()];
        boolean[] description = new boolean[words.size()];
        Arrays.fill(foreign, false);
        Arrays.fill(translation, false);
        Arrays.fill(description, false);
        if("direct".equals(translateDirection)) {
            Arrays.fill(foreign, true);
            model.addAttribute("foreign", foreign);
            model.addAttribute("translation", translation);
            model.addAttribute("description", description);
        } else {
            Arrays.fill(translation, true);
            model.addAttribute("foreign", foreign);
            model.addAttribute("translation",translation);
            model.addAttribute("description", description);
        }
        model.addAttribute("tags",
                tagService.getAll( ).stream()
                        .map(tagTransformer::fromEntity)
                        .collect(Collectors.toList()));
        redirectAttributes.addFlashAttribute("translateDirection", translateDirection);
        return "practice-tag";
    }


    @PostMapping(value = {"/{id}"})
    public String postTagForPractice(@PathVariable (name = "id") long id,
                                     @ModelAttribute(name = "translateDirection") String translateDirection,
                                     @RequestParam(name = "rowNumber") int rowNumber,
                                     @RequestParam(name = "foreign") boolean[] foreign,
                                     @RequestParam(name = "translation") boolean[] translation,
                                     @RequestParam(name = "description") boolean[] description,
                                    Model model) {
        Tag tag = tagService.readById(id);
        List<WordResponseForCRUD> words = tag.getWords().stream()
                .map(wordTransformer::fromEntityForCRUD)
                .toList();
        List<WordResponseForCRUD> toPractice = new ArrayList<>(words);
        Collections.shuffle(toPractice);
        model.addAttribute("words", toPractice);
        model.addAttribute("tag", tag);
        foreign[rowNumber] = true;
        translation[rowNumber] = true;
        description[rowNumber] = true;
        model.addAttribute("foreign", foreign);
        model.addAttribute("translation", translation);
        model.addAttribute("description", description);
        model.addAttribute("tags",
                tagService.getAll( ).stream()
                        .map(tagTransformer::fromEntity)
                        .collect(Collectors.toList()));
        model.addAttribute("rowNumber", rowNumber);
        return "practice-tag";
    }
}
