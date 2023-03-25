package com.georgy.taskService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.georgy.taskService.model.Task;
import com.georgy.taskService.model.TaskStatus;
import com.georgy.taskService.repo.TaskRepository;
import com.georgy.taskService.service.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.hamcrest.CoreMatchers.is;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc
class TaskServiceApplicationTests{
    @Autowired
    private MockMvc mock;
    @Autowired
    private TaskServiceImpl underTest;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        taskRepository.deleteAll();
    }

    @Test
    void itShouldSaveNewTaskAndThenReturnSavedTask() throws Exception{
        Task task = Task.builder()
                .id(1)
                .userId(1)
                .name("Transfer")
                .description("Send money!")
                .taskDate(LocalDate.of(2022, 9, 9))
                .status(TaskStatus.DUE)
                .build();
        ResultActions response = mock.perform(post("/api/task/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task)));
        response .andExpect(status().isCreated())
                 .andDo(print())
                 .andExpect(status().isCreated()).andReturn();
    }

    @Test
    void itShouldGetListOfAllTasks() throws Exception{
        Task task = new Task(1, 1, "Transfer", "Send money!", LocalDate.of(2022, 9, 9), TaskStatus.DUE);
        Task task2 = new Task(2, 1, "Receiving", "Get money!", LocalDate.of(2022, 5, 13), TaskStatus.OVERDUE);
        Task task3 = new Task(3, 1, "Account", "Create account!", LocalDate.of(2002, 12, 2), TaskStatus.DUE);

        List<Task> listOfTasks = new ArrayList<>();

        listOfTasks.add(task);
        listOfTasks.add(task2);
        listOfTasks.add(task3);

        taskRepository.saveAll(listOfTasks);

        ResultActions response = mock.perform(get("/api/task/all"));
        response.andExpect(status().isOk())
                .andDo(print());

    }
    @Test
    void itShouldGetListOfOverDueTasks() throws Exception{
        Task task = new Task(1, 1, "Transfer", "Send money!", LocalDate.of(2022, 9, 9), TaskStatus.DUE);
        Task task2 = new Task(2, 1, "Receiving", "Get money!", LocalDate.of(2022, 5, 13), TaskStatus.OVERDUE);
        Task task3 = new Task(3, 1, "Account", "Create account!", LocalDate.of(2002, 12, 2), TaskStatus.DUE);

        List<Task> listOfTasks = new ArrayList<>();

        listOfTasks.add(task);
        listOfTasks.add(task2);
        listOfTasks.add(task3);

        taskRepository.saveAll(listOfTasks);

        ResultActions response = mock.perform(get("/api/task/overdue"));
        response.andExpect(status().isOk())
                .andDo(print());
    }
}





















