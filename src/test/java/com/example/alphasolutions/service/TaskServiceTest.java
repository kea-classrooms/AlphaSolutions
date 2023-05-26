package com.example.alphasolutions.service;

import com.example.alphasolutions.DTOs.ProjectDTO;
import com.example.alphasolutions.DTOs.TasksDTO;
import com.example.alphasolutions.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    private TaskService taskService;

    private TasksDTO testTask = new TasksDTO(1, "test", "testDesc", 1, 2, null, 0, Date.valueOf(LocalDate.now()));
    private TasksDTO testTask2 = new TasksDTO(2, "test2", "testDesc2", 12, 22, null, 1, Date.valueOf(LocalDate.now()));

    private ProjectDTO testProject = new ProjectDTO(1, "test project", 2);

    @BeforeEach
    void setUp() throws FileNotFoundException {
        MockitoAnnotations.openMocks(this);
        taskService = new TaskService(mock(ApplicationContext.class), "taskRepository");
        taskService.taskRepository = taskRepository;
        taskService.resetDatabase();
    }

    @Test
    void testGetTasks() {
        // Arrange
        int projectId = 1;
        List<TasksDTO> expectedTasks = new ArrayList<>();
        expectedTasks.add(testTask);
        when(taskRepository.getTasks(projectId)).thenReturn(expectedTasks);

        // Act
        List<TasksDTO> actualTasks = taskService.getTasks(projectId);

        // Assert
        assertEquals(expectedTasks, actualTasks);
        verify(taskRepository, times(1)).getTasks(projectId);
    }

    @Test
    void testAddTask() {
        // Arrange
        TasksDTO taskToAdd = testTask;

        // Act
        taskService.addTask(taskToAdd);

        // Assert
        verify(taskRepository, times(1)).addTask(taskToAdd);
    }

    @Test
    void testGetTask() {
        // Arrange
        int taskId = 1;
        TasksDTO expectedTask = testTask;
        when(taskRepository.getTask(taskId)).thenReturn(expectedTask);

        // Act
        TasksDTO actualTask = taskService.getTask(taskId);

        // Assert
        assertEquals(expectedTask, actualTask);
        verify(taskRepository, times(1)).getTask(taskId);
    }

    @Test
    void testGetAllProjects() {
        // Arrange
        List<ProjectDTO> expectedProjects = new ArrayList<>();
        expectedProjects.add(testProject);
        when(taskRepository.getAllProjects()).thenReturn(expectedProjects);

        // Act
        List<ProjectDTO> actualProjects = taskService.getAllProjects();

        // Assert
        assertEquals(expectedProjects, actualProjects);
        verify(taskRepository, times(1)).getAllProjects();
    }

    @Test
    void testGetProject() {
        // Arrange
        int projectId = 1;
        ProjectDTO expectedProject = testProject;
        when(taskRepository.getProject(projectId)).thenReturn(expectedProject);

        // Act
        ProjectDTO actualProject = taskService.getProject(projectId);

        // Assert
        assertEquals(expectedProject, actualProject);
        verify(taskRepository, times(1)).getProject(projectId);
    }

    @Test
    void testDeleteTask() {
        // Arrange
        int taskId = 1;

        // Act
        taskService.deleteTask(taskId);

        // Assert
        verify(taskRepository, times(1)).deleteTask(taskId);
    }

    @Test
    void testGetTaskWithUpdatedCost() {
        // Arrange
        int projectId = 1;
        List<TasksDTO> tasks = new ArrayList<>();
        TasksDTO task1 = testTask;
        task1.setCost(10);
        TasksDTO task2 = testTask2;
        task2.setCost(20);
        task1.setSubtasks(List.of(task2));
        tasks.add(task1);
        when(taskRepository.getTasks(projectId)).thenReturn(tasks);

        // Act
        List<TasksDTO> updatedTasks = taskService.getTaskWithUpdatedCost(projectId);

        // Assert
        assertEquals(tasks.size(), updatedTasks.size());
        assertEquals(30, updatedTasks.get(0).getCost()); // Asserting the updated cost of the first task
        assertEquals(20, updatedTasks.get(0).getSubtasks().get(0).getCost()); // Asserting the updated cost of the subtask
        // Add more assertions as per your specific requirements and the structure of your DTOs
        verify(taskRepository, times(1)).getTasks(projectId);
    }


    @Test
    void testUpdateTask() {
        // Arrange
        TasksDTO updatedTask = testTask;

        // Act
        taskService.updateTask(updatedTask);

        // Assert
        verify(taskRepository, times(1)).updateTask(updatedTask);
    }
}