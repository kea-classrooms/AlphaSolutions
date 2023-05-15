package com.example.alphasolutions.controller;

import com.example.alphasolutions.DTOs.TasksDTO;
import com.example.alphasolutions.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TasksController {
    TaskService taskService;

    public TasksController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String index(Model model) { //Metoden er klar til at sende data fra service til template
        List<TasksDTO> tasks = taskService.getTasks();
        model.addAttribute("tasks", tasks);
        return "tasks/task-overview";
    }
    @GetMapping("/addTask")
    public String add(Model model){
        TasksDTO taskToAdd = new TasksDTO();
        model.addAttribute("taskToAdd", taskToAdd);
        return "tasks/create-task-form";
    }
    @PostMapping("/addTask")
    public String addTask(@ModelAttribute("taskForm") TasksDTO tasksDTO) {
       taskService.addTask(tasksDTO);
        return "redirect:/";
    }

    @GetMapping("/viewTask/{id}")
    public String viewTask(Model model, @PathVariable int id){
        TasksDTO task = taskService.getTask(id);
        model.addAttribute("task", task);
        return "tasks/view-task";
    }
}