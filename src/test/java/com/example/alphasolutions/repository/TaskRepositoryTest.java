package com.example.alphasolutions.repository;

import com.example.alphasolutions.DTOs.TasksDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskRepositoryTest {

    private TaskRepository taskRepository;
    private int testProjectDef = 1;
    private int tasksInitSize;
    Date today;

    @BeforeEach
    public void setup() throws FileNotFoundException {
        today = Date.valueOf(LocalDate.now());
        taskRepository = new TaskRepository();
        taskRepository.resetDatabase(true);
        tasksInitSize = taskRepository.getTasks(testProjectDef).size();
    }

    @Test
    public void testGetTasks() {
        List<TasksDTO> tasks = taskRepository.getTasks(testProjectDef);
        //Test all taskDTOs are created correctly
        for (TasksDTO task : tasks) {
            assertEquals(1, task.getProjectID());
        }
        //Test that tasks is not null
        assertNotNull(tasks);
        //Test size of tasks list
        assertEquals(3, tasks.size());
    }

    @Test
    public void testAddTask() {
        TasksDTO taskToAdd = new TasksDTO(
                1000,
                "testTask",
                "This is a test task",
                999,
                5,
                null,
                0,
                today
        );
        taskToAdd.setProjectID(testProjectDef);
        taskRepository.addTask(taskToAdd);
        //Test that a task was added
        assertEquals(tasksInitSize + 1, taskRepository.getTasks(testProjectDef).size());
    }

    @Test
    public void testGetTask() {
        TasksDTO task = taskRepository.getTask(1);
        //Test that task was found
        assertNotNull(task);

        //Test some data on the taskDTO
        assertEquals(1, task.getTaskID());
        assertEquals("oprette tasks", task.getTaskName());
        assertEquals("Oprette tasks så det kan sees i webappen", task.getTaskDescription());
        assertEquals(1, task.getProjectID());

    }

    @Test
    public void testUpdateTask() {
        TasksDTO updatedTask = new TasksDTO(
                1,
                "updated task",
                "This task has been updated",
                420,
                69,
                null,
                0,
                today
        );
        taskRepository.updateTask(updatedTask);
        // Tests values of updated task
        TasksDTO task = taskRepository.getTask(1);
        assertEquals(1, task.getTaskID());
        assertEquals("updated task", task.getTaskName());
        assertEquals("This task has been updated", task.getTaskDescription());
        assertEquals(420, task.getCost());
        assertEquals(69, task.getTotalEstimatedTime());
        assertEquals(testProjectDef, task.getProjectID());
        // Retrieve the task by its ID and assert its properties
    }

    @Test
    public void testDeleteTask() {
        // Specify an existing task ID to delete
        int taskId = 1;
        // Get number of subtasks for the task
        int numberOfSubtasks = taskRepository.getTask(taskId).getSubtasks().size();
        taskRepository.deleteTask(taskId);
        //Test that tasks shrunk by the number of subtasks + 1 as subtasks should be deleted when supertask is deleted
//        assertEquals(tasksInitSize - 1 - numberOfSubtasks, taskRepository.getTasks(testProjectDef).size());
    }

}
