package com.georgy.taskService.interfaces;

import com.georgy.taskService.model.Task;
import java.io.Serializable;
import java.util.List;
public interface Operations{
    List<Task> getListOfTasks();
    Task findOne(final int id);
    Task create(final Task resource);
    void update(final Task resource);
    void delete(final int id);
}
