package com.example.alphasolutions.service;

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

    public TaskService(ApplicationContext context, @Value("${as.repository.impl}") String impl) { //her tages value og sættes ind i impl
        taskRepository = (TaskRepository) context.getBean(impl); //context.getBean bliver converted til AlphaSolutionsDatabse
    }

    public List<TasksDTO> getTasks() {
        return taskRepository.getTasks();
    }
    public void addTask(TasksDTO taskToAdd) {
        //Simple service method, these might get longer and more convoluted later
        taskRepository.addTask(taskToAdd.getTaskName(),taskToAdd.getTaskDescription(), taskToAdd.getCost(), taskToAdd.getTotalEstimatedTime());
    }
}