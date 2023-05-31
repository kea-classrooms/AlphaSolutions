package com.example.alphasolutions.controller;
import com.example.alphasolutions.DTOs.ProjectDTO;
import com.example.alphasolutions.DTOs.TasksDTO;
import com.example.alphasolutions.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TasksControllerTest {

    @Mock
    private TaskService taskService;

    @Mock
    private Model model;

    @InjectMocks
    private TasksController tasksController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void homeTest() {
        String expectedViewName = "welcome";

        String viewName = tasksController.home(model);

        assertEquals(expectedViewName, viewName);
        verify(model).addAttribute(eq("auth"), any());
    }

    @Test
    void projectsOverviewTest() {
        String expectedViewName = "tasks/projects-overview";
        List<ProjectDTO> projects = new ArrayList<>();
        when(taskService.getAllProjects()).thenReturn(projects);

        String viewName = tasksController.projectsOverview(model);

        assertEquals(expectedViewName, viewName);
        verify(model).addAttribute(eq("projects"), eq(projects));
    }

    @Test
    void viewProjectsTest() {
        int projectId = 1;
        String expectedViewName = "tasks/view-project";
        ProjectDTO project = new ProjectDTO();
        List<TasksDTO> tasks = new ArrayList<>();
        when(taskService.getProject(projectId)).thenReturn(project);
        when(taskService.getTaskWithUpdatedCostAndTime(projectId)).thenReturn(tasks);

        String viewName = tasksController.viewProjects(model, projectId);

        assertEquals(expectedViewName, viewName);
        verify(model).addAttribute(eq("project"), eq(project));
        verify(model).addAttribute(eq("tasks"), eq(tasks));
    }

    @Test
    void addTest() {
        int projectId = 1;
        String expectedViewName = "tasks/create-task-form";
        List<TasksDTO> allTasks = new ArrayList<>();
        when(taskService.getTasks(projectId)).thenReturn(allTasks);

        String viewName = tasksController.add(model, projectId);

        assertEquals(expectedViewName, viewName);
        verify(model).addAttribute(eq("taskToAdd"), any());
        verify(model).addAttribute(eq("projectID"), eq(projectId));
        verify(model).addAttribute(eq("allTasks"), eq(allTasks));
    }

    @Test
    void addTaskTest() {
        int projectId = 1;
        TasksDTO tasksDTO = new TasksDTO();
        String expectedRedirectUrl = "redirect:/viewProject/1";

        String redirectUrl = tasksController.addTask(tasksDTO, projectId);

        assertEquals(expectedRedirectUrl, redirectUrl);
        assertEquals(projectId, tasksDTO.getProjectID());
        verify(taskService).addTask(eq(tasksDTO));
    }

    @Test
    void viewTaskTest() {
        int taskId = 1;
        String expectedViewName = "tasks/view-task";
        TasksDTO task = new TasksDTO();
        when(taskService.getTask(taskId)).thenReturn(task);

        String viewName = tasksController.viewTask(model, taskId);

        assertEquals(expectedViewName, viewName);
        verify(model).addAttribute(eq("task"), eq(task));
    }

    @Test
    void showUpdateFormTest() {
        int taskId = 1;
        String expectedViewName = "tasks/update-task-form";
        TasksDTO taskToUpdate = new TasksDTO();
        when(taskService.getTask(taskId)).thenReturn(taskToUpdate);

        String viewName = tasksController.showUpdateForm(taskId, model);

        assertEquals(expectedViewName, viewName);
        verify(model).addAttribute(eq("taskToUpdate"), eq(taskToUpdate));
    }

    @Test
    void updateTaskTest() {
        int taskId = 1;
        TasksDTO updatedTask = new TasksDTO();
        String expectedRedirectUrl = "redirect:/";

        String redirectUrl = tasksController.updateTask(taskId, updatedTask);

        assertEquals(expectedRedirectUrl, redirectUrl);
        assertEquals(taskId, updatedTask.getTaskID());
        verify(taskService).updateTask(eq(updatedTask));
    }

    @Test
    void deleteTest() {
        int taskId = 1;
        String expectedRedirectUrl = "redirect:/";

        String redirectUrl = tasksController.delete(taskId);

        assertEquals(expectedRedirectUrl, redirectUrl);
        verify(taskService).deleteTask(eq(taskId));
    }
}
