package com.georgy.taskService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Task{
    @Id
    @GeneratedValue
    private int id;kljljlk
    private int userId;
    private String name;
    private String description;
    private LocalDate taskDate;
    private TaskStatus status;

    public Task(int userId, String name, String description, LocalDate taskDate, TaskStatus status){
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.taskDate = taskDate;
        this.status = status;
    }
}
