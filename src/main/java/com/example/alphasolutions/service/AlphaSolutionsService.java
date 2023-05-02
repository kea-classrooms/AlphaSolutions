package com.example.alphasolutions.service;

import com.example.alphasolutions.DTOs.NameDTO;
import com.example.alphasolutions.repository.AlphaSolutionsDataBase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlphaSolutionsService {

    AlphaSolutionsDataBase alphaSolutionsDataBase;

    public AlphaSolutionsService(ApplicationContext context, @Value("${as.repository.impl}")String impl) { //her tages value og sættes ind i impl
     alphaSolutionsDataBase = (AlphaSolutionsDataBase) context.getBean(impl); //context.getBean bliver converted til AlphaSolutionsDatabse
    }

    public List<NameDTO> getNames() {
        return alphaSolutionsDataBase.getNames();

    }

    public void addName(NameDTO nameToAdd) {
        alphaSolutionsDataBase.addName(nameToAdd.getName());
    }
}