package com.example.alphasolutions.controller;

import com.example.alphasolutions.DTOs.EmployeeDTO;
import com.example.alphasolutions.DTOs.TasksDTO;
import com.example.alphasolutions.service.AlphaSolutionsService;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class NamesController {
    AlphaSolutionsService asService;

    public NamesController(AlphaSolutionsService asService) {
        this.asService = asService;
    }

    @GetMapping("/")
    public String index(Model model) { //Metoden er klar til at sende data fra service til template
        List<TasksDTO> tasks = asService.getTasks();
        model.addAttribute("tasks", tasks);
        return "tasks/task-overview";
    }
    @GetMapping("/addTask")
    public String add(Model model){
        TasksDTO taskToAdd = new TasksDTO();
        model.addAttribute("taskToAdd", taskToAdd);
        return "names/Create-Task-form";
    }
    @PostMapping("/addTask")
    public String addTask(@ModelAttribute("taskForm") TasksDTO tasksDTO) {
       asService.addTask(tasksDTO);
        return "redirect:/";
    }
}

   /* @GetMapping("/")
    public String index(Model model) { //Metoden er klar til at sende data fra service til template
        List<EmployeeDTO> employees = asService.getEmployees();
        model.addAttribute("employees", employees);
        return "names/index";
    }

    */


/*
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

    */
