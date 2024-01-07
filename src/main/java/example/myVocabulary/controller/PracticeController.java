package example.myVocabulary.controller;

import example.myVocabulary.dto.TagRequest;
import example.myVocabulary.dto.TagTransformer;
import example.myVocabulary.dto.WordTransformer;
import example.myVocabulary.model.Tag;
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
@RequestMapping("/practice")
@RequiredArgsConstructor
public class PracticeController {
    private final TagService tagService;
    private final TagTransformer tagTransformer;
    private final WordService wordService;
    private final WordTransformer wordTransformer;

    @GetMapping(value = {"/"})
    public String getAllTags(Model model) {
        model.addAttribute("tags",
        tagService.getAll( ).stream()
                .map(tagTransformer::fromEntity)
                .collect(Collectors.toList()));
    return "practice";
    }
//
//    @GetMapping(value = {"/{id}"})
//    public String getAllTags(@PathVariable (name = "id") long id,  Model model) {
//        model.addAttribute("words",
//                tagService.readById(id).getWords().stream()
//                        .map(wordTransformer::fromEntityForCRUD)
//                        .collect(Collectors.toList()));
//        model.addAttribute("tagName", tagService.readById(id).getName());
//        return "tag/tag-words";
//    }
//
//    @GetMapping(value = {"/create"})
//    public String getCreateTag(Model model) {
//        model.addAttribute("tagRequest", new TagRequest());
//        model.addAttribute("tags",
//                tagService.getAll().stream()
//                        .map(tagTransformer::fromEntity)
//                        .collect(Collectors.toList()));
//        return "tag/create";
//    }
//
//    @PostMapping(value = {"/create"})
//    public String postCreateTag(@ModelAttribute(name="tagRequest") @Valid TagRequest tagRequest, BindingResult bindingResult, Model model) {
//        List<String> errors = tagService.getTagErrors(tagRequest.getName(), bindingResult);
//        if (errors.isEmpty()) {
//            Tag newTag = tagService.create(tagTransformer.toEntityFromRequest(tagRequest));
//            return "redirect:/tags/" + newTag.getId();
//        } else {
//            model.addAttribute("errors", errors);
//            model.addAttribute("tags",
//                    tagService.getAll().stream()
//                            .map(tagTransformer::fromEntity)
//                            .collect(Collectors.toList()));
//            return "/tag/create";
//        }
//    }
//
//    @GetMapping(value = {"/{id}/delete"})
//    public String postDeleteTag(@PathVariable (name = "id") long id,  Model model,
//                                RedirectAttributes redirectAttributes) {
//        if (tagService.readById(id).getWords().isEmpty()) {
//            tagService.delete(id);
//        } else {
//            Object errorId = id;
//            redirectAttributes.addFlashAttribute("errorId", errorId);
//        }
//        return "redirect:/tags/";
//    }
//
//    @GetMapping("/{id}/update")
//    public String getUpdateTag(@PathVariable long id, Model model) {
//    model.addAttribute("tag", tagService.readById(id));
//        model.addAttribute("tags",
//                tagService.getAll().stream()
//                        .map(tagTransformer::fromEntity)
//                        .collect(Collectors.toList()));
//        return "tag/update";
//    }
//
//    @PostMapping("/{id}/update")
//    public String postUpdateTag(@PathVariable long id,
//                                @ModelAttribute(name="tag") @Valid Tag tag,
//                                BindingResult bindingResult, Model model) {
//        List<String> errors = tagService.getTagErrors(tag.getName(), bindingResult);
//        if (errors.isEmpty()) {
//            tagService.update(tag);
//            return "redirect:/tags/";
//        } else {
//            model.addAttribute("errors", errors);
//            model.addAttribute("tags",
//                    tagService.getAll().stream()
//                            .map(tagTransformer::fromEntity)
//                            .collect(Collectors.toList()));
//            return "/tag/update";
//        }
//    }

}
