package com.georgy.taskService.controller;

import com.georgy.taskService.model.Task;
import com.georgy.taskService.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("api/task")
public class TaskController{
    @Autowired
    private TaskServiceImpl taskService;

    @GetMapping("/all")
    public List<Task> getAllTasks(){
        return taskService.getListOfTasks();
    }

    @GetMapping("/overdue")
    public List<Task> geAllOverDueTasks(){
        return taskService.listOfOverdueTasks();
    }

    @GetMapping("/due")
    public List<Task> geAllDueTasks(){
        return taskService.listOfDueTasks();
    }

    @GetMapping("/date")
    public List<Task> geAllTasksByDate(@RequestParam(value = "taskDate")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate taskDate){
         return taskService.geAllTasksByDate(taskDate);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Task task){
        RestPreConditions.checkRequestElementNotNull(task);
        taskService.create(task);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") int id, @RequestBody Task task){
        RestPreConditions.checkRequestState(task.getId() == id);
        RestPreConditions.checkNotNull(task);
        taskService.update(task);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id){
        taskService.delete(id);
    }

    @GetMapping("/{id}")
    public Task findOne(@PathVariable("id") int id){
       return RestPreConditions.checkNotNull(taskService.findOne(id));
    }

}
