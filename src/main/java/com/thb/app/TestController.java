package com.thb.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class TestController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Meme's for Monday");
        return "index";
    }
}