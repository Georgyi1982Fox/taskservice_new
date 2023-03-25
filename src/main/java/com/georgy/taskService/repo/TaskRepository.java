package com.georgy.taskService.repo;

import com.georgy.taskService.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;
public interface TaskRepository extends JpaRepository<Task ,Integer>{
}
