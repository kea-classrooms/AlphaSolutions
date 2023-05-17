package com.example.alphasolutions.controller;

import com.example.alphasolutions.DTOs.ProjectDTO;
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

    // This method maps to the root URL of the web application and sends the list of projects to the view
    @GetMapping("/")
    public String index(Model model) {
        // Get the list of projects from the service
        List<ProjectDTO> projects = taskService.getAllProjects();

        // Add the list of projects to the model object to be passed to the view
        model.addAttribute("projects", projects);

        // Return the name of the overview template to be rendered
        return "index";
    }

    // This method maps to the "viewProject" URL of the web application and sends the list of tasks in the project to the view
    @GetMapping("/viewProject/{id}")
    public String viewProjects(Model model, @PathVariable int id) {
        ProjectDTO project = taskService.getProject(id);
        model.addAttribute("project", project);

        // Get the list of tasks from the service
        List<TasksDTO> tasks = taskService.getTasks(id);

        // Add the list of tasks to the model object to be passed to the view
        model.addAttribute("tasks", tasks);

        // Return the name of the overview template to be rendered
        return "tasks/project-overview";
    }

    // This method maps to the "addTask" URL and sends a blank task object to the view
    //This is a blank object with no data, and is used to populate a form in the view template for the user to fill out.
    @GetMapping("/viewProject/{id}/addTask")
    public String add(Model model, @PathVariable int id) {
        TasksDTO taskToAdd = new TasksDTO();
        taskToAdd.setProjectID(id);

        // Add the blank task object to the model object to be passed to the view
        model.addAttribute("taskToAdd", taskToAdd);
        model.addAttribute("projectID", id);

        List<TasksDTO> allTasks = taskService.getTasks(id);
        model.addAttribute("allTasks", allTasks);

        // Return the name of the view template to be rendered
        return "tasks/create-task-form";
    }


    // This method maps to the "addTask" URL and adds a new task to the database
    @PostMapping("/viewProject/{id}/addTask")
    public String addTask(@ModelAttribute("taskForm") TasksDTO tasksDTO, @PathVariable int id) {
        // Call the service to add the new task to the database
       taskService.addTask(tasksDTO);
       //'redirect' specifies that this endpoint should just point to another endpoint, in this case '/'
        return String.format("redirect:/viewProject/%d", id);
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