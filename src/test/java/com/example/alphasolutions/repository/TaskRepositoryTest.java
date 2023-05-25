package com.example.alphasolutions.repository;

import com.example.alphasolutions.DTOs.TasksDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@SpringBootTest
public class TaskRepositoryTest {

    private TaskRepository taskRepository;

    @BeforeEach
    public void setup() {
        taskRepository = new TaskRepository();
    }

    @Test
    public void testGetTasks() {
        List<TasksDTO> tasks = taskRepository.getTasks(1);
        // Add assertions to verify the correctness of the retrieved tasks
        Assertions.assertNotNull(tasks);
        Assertions.assertFalse(tasks.isEmpty());
        // Add more assertions as needed
    }

    @Test
    public void testAddTask() {
        Calendar cal = Calendar.getInstance();
        TasksDTO taskToAdd = new TasksDTO(
                1000,
                "testTask",
                "This is a test task",
                999,
                5,
                null,
                0,
                Date.valueOf(LocalDate.of(1998, 9, 29))
        );
        taskToAdd.setProjectID(1);
        taskRepository.addTask(taskToAdd);
        // Add assertions to verify that the task was successfully added to the database
        // You can retrieve the task by its ID and assert its properties
    }

    @Test
    public void testGetTask() {
        TasksDTO task = taskRepository.getTask(1);
        // Add assertions to verify the correctness of the retrieved task
        Assertions.assertNotNull(task);
        // Add more assertions as needed
    }

    @Test
    public void testUpdateTask() {
        TasksDTO updatedTask = new TasksDTO(/* Set up the updated task object with new properties */);
        taskRepository.updateTask(updatedTask);
        // Add assertions to verify that the task was successfully updated in the database
        // Retrieve the task by its ID and assert its properties
    }

    @Test
    public void testDeleteTask() {
        int taskId = 1; // Specify an existing task ID to delete
        taskRepository.deleteTask(taskId);
        // Add assertions to verify that the task was successfully deleted from the database
        // You can try to retrieve the task and assert that it is null or not found
    }

}
