package com.georgy.taskService.service;

import com.georgy.taskService.exceptions.EntityNotFoundException;
import com.georgy.taskService.interfaces.Operations;
import com.georgy.taskService.model.Task;
import com.georgy.taskService.repo.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractService implements Operations{

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private TaskRepository taskRepository;
    public AbstractService(){
        super();
    }
    @Override
    public List<Task> getListOfTasks(){
        return taskRepository.findAll();
    }
    @Override
    public Task findOne(final int id){
        return taskRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Task create(final Task entity){
        Objects.requireNonNull(entity);
        final Task persistedEntity = taskRepository.save(entity);
        return persistedEntity;
    }
    @Override
    public void update(final Task entity){
        Objects.requireNonNull(entity);
        taskRepository.save(entity);
    }
    @Override
    public void delete(final int id){
        final Task entity = taskRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        taskRepository.delete(entity);
    }
}
