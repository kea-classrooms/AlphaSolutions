package com.example.alphasolutions.controller;

import com.example.alphasolutions.DTOs.ProjectDTO;
import com.example.alphasolutions.DTOs.TasksDTO;
import com.example.alphasolutions.service.TaskService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public String home(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("auth", auth);
        return "welcome";
    }

    // This method maps to the root URL of the web application and sends the list of projects to the view
    @GetMapping("/projects")
    public String projectsOverview(Model model) {
        // Get the list of projects from the service
        List<ProjectDTO> projects = taskService.getAllProjects();

        // Add the list of projects to the model object to be passed to the view
        model.addAttribute("projects", projects);

        // Return the name of the overview template to be rendered
        return "tasks/projects-overview";
    }
    @GetMapping("/create-project")
    public String showCreateProjectForm(Model model) {
        model.addAttribute("projectToAdd", new ProjectDTO());
        return "tasks/create-project-form";
    }

    // This method maps to the "viewProject" URL of the web application and sends the list of tasks in the project to the view
    @GetMapping("/viewProject/{id}")
    public String viewProjects(Model model, @PathVariable int id) {
        //Get the current project, and add it to the model
        ProjectDTO project = taskService.getProject(id);
        model.addAttribute("project", project);

        // Get the list of tasks from the service
        List <TasksDTO> tasks = taskService.getTaskWithUpdatedCostAndTime(id);

        // Add the list of tasks to the model object to be passed to the view
        model.addAttribute("tasks", tasks);

        // Return the name of the overview template to be rendered
        return "tasks/view-project";
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
    @GetMapping("/updateTask/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        // Retrieve the task from the database using the id
        TasksDTO taskToUpdate = taskService.getTask(id);

        // Add the taskToUpdate object to the model
        model.addAttribute("taskToUpdate", taskToUpdate);

        // Retrieve the list of all tasks and add it to the model
        List<TasksDTO> allTasks = taskService.getTasks(taskToUpdate.getProjectID());
        model.addAttribute("allTasks", allTasks);

        // Return the view name
        return "tasks/update-task-form";
    }

    // This method maps to the "updateTask/{id}" URL and handles the update request
    @PostMapping("/updateTask/{id}")
    public String updateTask(@PathVariable int id, @ModelAttribute("taskToUpdate") TasksDTO updatedTask) {
        // Set the task ID for the updated task
        updatedTask.setTaskID(id);
        int projectID = taskService.getTask(id).getProjectID();
        updatedTask.setProjectID(projectID);
        // Call the service to update the task in the database
        taskService.updateTask(updatedTask);

        // Redirect to the task details page after the update
        return String.format("redirect:/viewProject/%d", updatedTask.getProjectID());
    }
    // This method maps to the "delete/{taskID}" URL and deletes a task from the database
    @GetMapping("/delete/{taskID}")
    public String delete(@PathVariable int taskID) {
        int projectID = taskService.getTask(taskID).getProjectID();
        // Call the service method to delete the task
        taskService.deleteTask(taskID);
        // Redirect to the root URL to refresh the task list
        return String.format("redirect:/viewProject/%d", projectID);
    }
}