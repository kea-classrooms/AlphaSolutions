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

    // This method maps to the root URL of the web application and sends the list of tasks to the view
    @GetMapping("/")
    public String index(Model model) {
        // Get the list of tasks from the service
        List<TasksDTO> tasks = taskService.getTasks();

        // Add the list of tasks to the model object to be passed to the view
        model.addAttribute("tasks", tasks);

        // Return the name of the overview template to be rendered
        return "tasks/task-overview";
    }

    // This method maps to the "addTask" URL and sends a blank task object to the view
    //This is a blank object with no data, and is used to populate a form in the view template for the user to fill out.
    @GetMapping("/addTask")
    public String add(Model model) {
        TasksDTO taskToAdd = new TasksDTO();

        // Add the blank task object to the model object to be passed to the view
        model.addAttribute("taskToAdd", taskToAdd);

        // Return the name of the view template to be rendered
        return "tasks/create-task-form";


    }
    @GetMapping("/addSubtask")
    public String addSubtask(Model model){
        TasksDTO subtaskToAdd = new TasksDTO();
        model.addAttribute("SubtaskToAdd", subtaskToAdd);
        return "tasks/create-subtask-form";
    }

    // This method maps to the "addTask" URL and adds a new task to the database
    @PostMapping("/addTask")
    public String addTask(@ModelAttribute("taskForm") TasksDTO tasksDTO) {
        // Call the service to add the new task to the database
        taskService.addTask(tasksDTO);

        // Redirect to the root URL to show the updated list of tasks
        return "redirect:/";
    }
    @PostMapping ("/addSubtask")
    public String addSubTask(@ModelAttribute("SubtaskForm") TasksDTO tasksDTO) {
       taskService.addTask(tasksDTO);
        return "redirect:/";
    }

    // This method maps to the "viewTask/{id}" URL and displays the details of a single task
    @GetMapping("/viewTask/{id}")
    public String viewTask(Model model, @PathVariable int id){
        // Get the task with the given ID from the service
        TasksDTO task = taskService.getTask(id);

        // Add the task object to the model object to be passed to the view
        model.addAttribute("task", task);

        // Return the name of the view template to be rendered
        return "tasks/view-task";
    }
}




