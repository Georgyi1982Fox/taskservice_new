package com.georgy.taskService.interfaces;

import com.georgy.taskService.model.Task;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
public interface TaskStatusOperations<T extends Serializable>{
    List<T> listOfOverdueTasks();
    List<T> listOfDueTasks();

}



