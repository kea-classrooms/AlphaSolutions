package com.example.alphasolutions.service;

import com.example.alphasolutions.DTOs.EmployeeDTO;
import com.example.alphasolutions.DTOs.NameDTO;
import com.example.alphasolutions.repository.NamesRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

/*@Service
public class NamesService {

    NamesRepository namesRepository;

    public NamesService(ApplicationContext context, @Value("${as.repository.impl}") String impl) { //her tages value og sættes ind i impl
        namesRepository = (NamesRepository) context.getBean(impl); //context.getBean bliver converted til AlphaSolutionsDatabse
    }

    public List<EmployeeDTO> getEmployees() {
        return namesRepository.getEmployees();

    }
}
/*
    public void addName(NameDTO nameToAdd) {
        //Simple service method, these might get longer and more convoluted later
        namesRepository.addName(nameToAdd.getName());
    }

    public void deleteName(String name) {
        namesRepository.deleteName(name);
    }
}

 */