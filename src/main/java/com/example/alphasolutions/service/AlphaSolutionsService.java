package com.example.alphasolutions.service;

import com.example.alphasolutions.DTOs.EmployeeDTO;
import com.example.alphasolutions.DTOs.TasksDTO;
import com.example.alphasolutions.repository.NamesRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class AlphaSolutionsService {

    NamesRepository namesRepository;

    public AlphaSolutionsService(ApplicationContext context, @Value("${as.repository.impl}") String impl) { //her tages value og sættes ind i impl
        namesRepository = (NamesRepository) context.getBean(impl); //context.getBean bliver converted til AlphaSolutionsDatabse
    }

    public List<TasksDTO> getTasks() {
        return namesRepository.getTasks();
    }
    public void addTask(TasksDTO taskToAdd) {
        //Simple service method, these might get longer and more convoluted later
        namesRepository.addTask(taskToAdd.getTaskName(),taskToAdd.getTaskDescription(), taskToAdd.getCost(), taskToAdd.getTotalEstimatedTime());
    }
}


        // public List<EmployeeDTO> getEmployees() {
     //   return namesRepository.getEmployees();


    //private static final Logger logger = LoggerFactory.getLogger(AlphaSolutionsService.class);


/*
    public List<NameDTO> getNames() {
        return alphaSolutionsDataBase.getNames();

    }

    public void addName(NameDTO nameToAdd) {
        //Simple service method, these might get longer and more convoluted later
        alphaSolutionsDataBase.addName(nameToAdd.getName());
    }

    public void deleteName(String name) {
        alphaSolutionsDataBase.deleteName(name);
    }

 */
