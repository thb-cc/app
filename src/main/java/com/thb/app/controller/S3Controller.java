package com.thb.app.controller;

import com.thb.app.service.S3Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class S3Controller {

    private final S3Service s3Service;

    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @GetMapping("/")
    public String showRandomImage(Model model) {

        int count = s3Service.getJpgCount();
        String imageBase64 = s3Service.getRandomJpgAsBase64();

        model.addAttribute("count", count);
        model.addAttribute("image", imageBase64);

        return "index";
    }

}
