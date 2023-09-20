package example.myVocabulary.controller;

import example.myVocabulary.service.TagService;
import example.myVocabulary.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final TagService tagService;
    private final WordService wordService;

    @GetMapping("/home")
    public String getHome(Model model) {
        //model.addAttribute("users", userService.getAll());
        return "home";
    }

    @GetMapping("/")
    public String getBlank(Model model) {
        //model.addAttribute("users", userService.getAll());

        return "home";
    }
}
