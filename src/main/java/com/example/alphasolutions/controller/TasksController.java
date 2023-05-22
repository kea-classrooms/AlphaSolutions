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
        return "index";
    }

    // This method maps to the "addTask" URL and sends a blank task object to the view
    //This is a blank object with no data, and is used to populate a form in the view template for the user to fill out.
    @GetMapping("/addTask")
    public String add(Model model) {
        TasksDTO taskToAdd = new TasksDTO();

        // Add the blank task object to the model object to be passed to the view
        model.addAttribute("taskToAdd", taskToAdd);

        List<TasksDTO> allTasks = taskService.getTasks();
        model.addAttribute("allTasks", allTasks);

        // Return the name of the view template to be rendered
        return "tasks/create-task-form";
    }


    // This method maps to the "addTask" URL and adds a new task to the database
    @PostMapping("/addTask")
    public String addTask(@ModelAttribute("taskForm") TasksDTO tasksDTO) {
        // Call the service to add the new task to the database
       taskService.addTask(tasksDTO);
       //'redirect' specifies that this endpoint should just point to another endpoint, in this case '/'
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
    // This method maps to the "updateTask/{id}" URL and handles the update request
    @PostMapping("/updateTask/{id}")
    public String updateTask(@PathVariable int id, @ModelAttribute("taskForm") TasksDTO updatedTask) {
        // Set the task ID for the updated task
        updatedTask.setTaskID(id);

        // Call the service to update the task in the database
        taskService.updateTask(updatedTask);

        // Redirect to the task details page after the update
        return "redirect:/viewTask/{id}";
    }
}