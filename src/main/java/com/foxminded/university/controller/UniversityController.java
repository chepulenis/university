package com.foxminded.university.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UniversityController {

    @GetMapping("/")
    public String viewHomePage(Model model) {
        return "index";
    }
}
