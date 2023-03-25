package com.georgy.taskService;

import com.georgy.taskService.controller.TaskController;
import com.georgy.taskService.model.Task;
import com.georgy.taskService.model.TaskStatus;
import com.georgy.taskService.repo.TaskRepository;
import com.georgy.taskService.service.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TaskApplicationUnitTests{
    @Autowired
    private TaskRepository repository;
    @Autowired
    private TaskServiceImpl service;

    @Test
    public void itShouldSaveNewTaskAndThenReturnNewTask(){
        Task task = new Task();
        task.setId(1);
        task.setUserId(1);
        task.setName("Transfer");
        task.setDescription("Send money!");
        task.setTaskDate(LocalDate.of(2022,9,9));
        task.setStatus(TaskStatus.DUE);

        repository.save(task);

        Optional<Task> optionalTask = repository.findById(1);

        assertThat(optionalTask).isPresent();
    }

    @Test
    public void itShouldReturnListOfAllTasks(){
        Task task = new Task(1, 1, "Transfer", "Send money!", LocalDate.of(2022, 9, 9), TaskStatus.DUE);
        Task task2 = new Task(2, 1, "Receiving", "Get money!", LocalDate.of(2022, 5, 13), TaskStatus.OVERDUE);
        Task task3 = new Task(3, 1, "Account", "Create account!", LocalDate.of(2002, 12, 2), TaskStatus.DUE);

        List<Task> listOfTasks = new ArrayList<>();

        listOfTasks.add(task);
        listOfTasks.add(task2);
        listOfTasks.add(task3);

        repository.saveAll(listOfTasks);

        List<Task> taskList = repository.findAll();
        assertThat(taskList.size()).isEqualTo(3);
    }

    @Test
    public void itShouldReturnListOfAllOverdueTasks(){
        Task task = new Task(1, 1, "Transfer", "Send money!", LocalDate.of(2022, 9, 9), TaskStatus.DUE);
        Task task2 = new Task(2, 1, "Receiving", "Get money!", LocalDate.of(2022, 5, 13), TaskStatus.OVERDUE);
        Task task3 = new Task(3, 1, "Account", "Create account!", LocalDate.of(2002, 12, 2), TaskStatus.OVERDUE);

        List<Task> listOfTasks = new ArrayList<>();

        listOfTasks.add(task);
        listOfTasks.add(task2);
        listOfTasks.add(task3);

        repository.saveAll(listOfTasks);

        List<Task> listOfOverdueTasks = service.listOfOverdueTasks();

        assertThat(listOfOverdueTasks.size()).isEqualTo(2);
    }

    @Test
    public void itShouldReturnListOfTasksByDate(){
        Task task = new Task(1, 1, "Transfer", "Send money!", LocalDate.of(2022, 12, 2), TaskStatus.DUE);
        Task task2 = new Task(2, 1, "Receiving", "Get money!", LocalDate.of(2022, 12, 2), TaskStatus.OVERDUE);
        Task task3 = new Task(3, 1, "Account", "Create account!", LocalDate.of(2022, 12, 2), TaskStatus.OVERDUE);

        List<Task> listOfTasks = new ArrayList<>();

        listOfTasks.add(task);
        listOfTasks.add(task2);
        listOfTasks.add(task3);

        repository.saveAll(listOfTasks);

        List<Task> listOfTasksByDate = service.geAllTasksByDate(LocalDate.of(2022,12,2));

        assertThat(listOfTasksByDate.size()).isEqualTo(3);

    }

    @Test
    public void itShouldUpdateTask(){
        Task task = new Task(1, 1, "Transfer", "Send money!", LocalDate.of(2022, 12, 2), TaskStatus.DUE);
        repository.save(task);
        Task taskById = repository.findById(1).get();
        taskById.setName("jj");
        repository.save(taskById);
        assertNotEquals("Transfer", taskById.getName());
    }

    @Test
    public void itShouldDeleteTask(){
        Task task = new Task(1, 1, "Transfer", "Send money!", LocalDate.of(2022, 12, 2), TaskStatus.DUE);
        repository.save(task);
        repository.deleteById(1);
        assertThat(repository.existsById(1)).isFalse();

    }
}






















