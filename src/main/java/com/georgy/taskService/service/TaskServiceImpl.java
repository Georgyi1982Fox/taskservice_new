package com.georgy.taskService.service;

import com.georgy.taskService.interfaces.TaskStatusOperations;
import com.georgy.taskService.model.Task;
import com.georgy.taskService.model.TaskStatus;
import com.georgy.taskService.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class TaskServiceImpl extends AbstractService implements TaskStatusOperations{
    @Autowired
    private TaskRepository taskRepository;

    public TaskServiceImpl(){
        super();
    }
    @Override
    public List<Task> listOfOverdueTasks(){
        List<Task> overDueTasks = taskRepository.findAll()
                .stream()
                .filter(c-> c.getStatus().equals(TaskStatus.OVERDUE))
                .collect(Collectors.toList());
        return overDueTasks;
    }
    @Override
    public List<Task> listOfDueTasks(){
        List<Task> dueTasks = taskRepository.findAll()
                .stream()
                .filter(c-> c.getStatus().equals(TaskStatus.DUE))
                .collect(Collectors.toList());
        return dueTasks;
    }
    public List<Task> geAllTasksByDate(LocalDate taskDate){
        List<Task> tasksByDate = taskRepository.findAll()
                .stream()
                .filter(c -> c.getTaskDate().equals(taskDate))
                .collect(Collectors.toList());
        return tasksByDate;
    }
}
