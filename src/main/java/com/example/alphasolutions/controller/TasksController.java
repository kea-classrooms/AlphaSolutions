package com.example.alphasolutions.controller;

import com.example.alphasolutions.DTOs.ProjectDTO;
import com.example.alphasolutions.DTOs.TasksDTO;
import com.example.alphasolutions.service.TaskService;
import org.springframework.scheduling.config.Task;
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
        //Get the current project, and add it to the model
        ProjectDTO project = taskService.getProject(id);
        model.addAttribute("project", project);

        // Get the list of tasks from the service
        List <TasksDTO> tasks = taskService.getTaskWithUpdatedCost(id);

        // Add the list of tasks to the model object to be passed to the view
        model.addAttribute("tasks", tasks);

        // Return the name of the overview template to be rendered
        return "tasks/project-overview";
    }

    // This method maps to the "/viewProject/{id}/addTask" URL and sends a blank task object to the view
    //This is a blank object with no data, and is used to populate a form in the view template for the user to fill out.
    @GetMapping("/viewProject/{id}/addTask")
    public String add(Model model, @PathVariable int id) {
        TasksDTO taskToAdd = new TasksDTO();

        // Add the blank task object and the projectID to the model object to be passed to the view
        model.addAttribute("taskToAdd", taskToAdd);
        model.addAttribute("projectID", id);

        //Add a list of all tasks from the current project to the view
        List<TasksDTO> allTasks = taskService.getTasks(id);
        model.addAttribute("allTasks", allTasks);

        // Return the name of the view template to be rendered
        return "tasks/create-task-form";
    }


    // This method maps to the "/viewProject/{id}/addTask" URL and adds a new task to the database
    @PostMapping("/viewProject/{id}/addTask")
    public String addTask(@ModelAttribute("taskForm") TasksDTO tasksDTO, @PathVariable int id) {
        //The tasks projectID is set here, as we know it has to be linked to the current project
        tasksDTO.setProjectID(id);
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
    // This method maps to the "delete/{taskID}" URL and deletes a task from the database
    @GetMapping("/delete/{taskID}")
    public String delete(@PathVariable int taskID) {
        // Call the service method to delete the task
        taskService.deleteTask(taskID);
        // Redirect to the root URL to refresh the task list
        return "redirect:/";
    }
}