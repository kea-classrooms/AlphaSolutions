package com.example.alphasolutions.service;

import com.example.alphasolutions.DTOs.EmployeeDTO;
import com.example.alphasolutions.repository.AlphaSolutionsDataBase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class AlphaSolutionsService {

    AlphaSolutionsDataBase alphaSolutionsDataBase;

    public AlphaSolutionsService(ApplicationContext context, @Value("${as.repository.impl}") String impl) { //her tages value og sættes ind i impl
        alphaSolutionsDataBase = (AlphaSolutionsDataBase) context.getBean(impl); //context.getBean bliver converted til AlphaSolutionsDatabse
    }

    public List<EmployeeDTO> getEmployee() {
        return alphaSolutionsDataBase.getEmployee();

    }
    //private static final Logger logger = LoggerFactory.getLogger(AlphaSolutionsService.class);

}
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
