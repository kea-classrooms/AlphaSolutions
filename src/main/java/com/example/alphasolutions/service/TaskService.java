package com.example.alphasolutions.service;

import com.example.alphasolutions.DTOs.ProjectDTO;
import com.example.alphasolutions.DTOs.TasksDTO;
import com.example.alphasolutions.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class TaskService {

    TaskRepository taskRepository;

    public TaskService(ApplicationContext context, @Value("${as.repository.impl}") String impl) { //value is taken and inserted in impl
        //Constructor with Dependency Injection.
        taskRepository = (TaskRepository) context.getBean(impl); //context.getBean gets converted to the database TaskRepository
    }

    public List<TasksDTO> getTasks(int id) {
        //This method returns a list of all tasks in project with a specific id.
        return taskRepository.getTasks(id);
    }
    public void addTask(TasksDTO taskToAdd) {
        //This method adds a new task to the database.
        //It accepts a TasksDTO object as a parameter, and calls the addTask method in the taskRepository object to add the task to the database.
        taskRepository.addTask(taskToAdd);
    }

    public TasksDTO getTask(int id) {
        //This method returns a task based on the task id provided.
        //It calls the getTask method in the taskRepository object to retrieve the task from the database.
        return taskRepository.getTask(id);
    }

    public List<ProjectDTO> getAllProjects() {
        //This method returns a list of all projects in out database.
        return taskRepository.getAllProjects();
    }

    public ProjectDTO getProject(int id) {
        return taskRepository.getProject(id);
    }
    public void deleteTask(int taskID) {
        taskRepository.deleteTask(taskID);
    }
}