package example.myVocabulary.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

//    @GetMapping("/403")
//    public String forbidden(Model model) {
//        return "error/403";
//    }

    @GetMapping("/404")
    public String notFound(Model model) {
        return "404";
    }

    @GetMapping("/500")
    public String internal(Model model) {
        return "500";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "error/access-denied";
    }
}
