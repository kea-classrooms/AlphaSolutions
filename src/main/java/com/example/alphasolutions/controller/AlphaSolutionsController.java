package com.example.alphasolutions.controller;

import com.example.alphasolutions.service.AlphaSolutionsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("as")
public class AlphaSolutionsController {
    AlphaSolutionsService asService;

    public AlphaSolutionsController(AlphaSolutionsService asService) {
        this.asService = asService;
    }


    @GetMapping("/")
    public String index(Model model){ //Metoden er klar til at sende data fra service til template
        List<String> names = asService.getNames();
        model.addAttribute("names", names);
        return "index";
    }


}
