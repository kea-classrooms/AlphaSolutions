package com.example.alphasolutions.controller;

import com.example.alphasolutions.DTOs.NameDTO;
import com.example.alphasolutions.service.AlphaSolutionsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AlphaSolutionsController {
    AlphaSolutionsService asService;

    public AlphaSolutionsController(AlphaSolutionsService asService) {
        this.asService = asService;
    }


    //Metoden er klar til at sende data fra service til template
    @GetMapping("/")
    public String index(Model model){
        List<NameDTO> names = asService.getNames();
        model.addAttribute("names", names);
        return "index";
    }

    // Just some test endpoints for adding names to our database through the WebApp
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
    } //Post Re-direct til get kommando

    @GetMapping("/delete/{name}")
    public String delete(Model model, @PathVariable String name){
        //Call service method
        asService.deleteName(name);

        //Setup a DTO to tell user which name was deleted
        NameDTO deletedName = new NameDTO(name);
        model.addAttribute("deletedName", deletedName);
        return "name-deleted";
    }
}
