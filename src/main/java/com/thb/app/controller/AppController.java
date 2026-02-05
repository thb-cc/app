package com.thb.app.controller;

import com.thb.app.service.QuoteService;
import com.thb.app.service.S3Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {

    private final S3Service s3Service;
    private final QuoteService quoteService;


    public AppController(S3Service s3Service, QuoteService quoteService) {
        this.s3Service = s3Service;
        this.quoteService = quoteService;
    }

    @GetMapping("/")
    public String showRandomImage(Model model) {

        int count = s3Service.getJpgCount();
        String imageBase64 = s3Service.getRandomJpgAsBase64();

        model.addAttribute("count", count);
        model.addAttribute("image", imageBase64);

        return "index";
    }

    @GetMapping("/quote")
    @ResponseBody
    public String newQuote() {
        return quoteService.getRandomQuote();
    }

}
