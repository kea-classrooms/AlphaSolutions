package com.example.alphasolutions.controller;

import com.example.alphasolutions.DTOs.NameDTO;
import com.example.alphasolutions.service.AlphaSolutionsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.Name;
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
        List<NameDTO> names = asService.getNames();
        model.addAttribute("names", names);
        return "index";
    }

    // Just some test endpoints for adding names to our database through the webapp
    @GetMapping("/add")
    public String add(Model model){
        NameDTO nameToAdd = new NameDTO();
        model.addAttribute("nameToAdd", nameToAdd);
        return "add-name-form";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("nameToAdd") NameDTO nameToAdd){
        asService.addName(nameToAdd);
        return "add-name-success";
    }

}
