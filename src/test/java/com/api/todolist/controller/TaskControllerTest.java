package com.api.todolist.controller;

import com.api.todolist.entity.Task;
import com.api.todolist.entity.TaskStatus;
import com.api.todolist.mapper.TaskMapper;
import com.api.todolist.model.TaskRequest;
import com.api.todolist.model.TaskResponse;
import com.api.todolist.service.TaskService;
import com.api.todolist.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    @MockBean
    TaskService taskService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    MockMvc mockMvc;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getAllTasksByUser() throws Exception {
        TaskResponse taskResponse = getTaskResponse();
        String token = getToken();

        when(taskService.findByUser(any(), any())).thenReturn(List.of(taskResponse));

        mockMvc.perform(get("/task/user")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    void getAllTasksByUser_without_token() throws Exception {
        mockMvc.perform(get("/task/user")).andExpect(status().isForbidden());
    }

    @Test
    void saveTask() throws Exception {
        String token = getToken();

        TaskRequest taskRequest = getTaskRequest();

        mockMvc.perform(post("/task/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(taskRequest))
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isAccepted());
    }

    @Test
    void saveTask_without_token() throws Exception {
        TaskRequest taskRequest = getTaskRequest();

        mockMvc.perform(post("/task/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(taskRequest)))
                .andExpect(status().isForbidden());
    }

    @Test
    void updateTask() throws Exception {
        Task task = getTask();

        when(taskService.save(any(),any())).thenReturn(TaskMapper.getTaskResponse(task));

        String token = getToken();

        TaskRequest taskRequest = getTaskRequest();
        taskRequest.setStatus(TaskStatus.COMPLETED);

        mockMvc.perform(put("/task/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(taskRequest))
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void updateTask_without_token() throws Exception {
        Task task = getTask();

        when(taskService.save(any(),any())).thenReturn(TaskMapper.getTaskResponse(task));

        TaskRequest taskRequest = getTaskRequest();
        taskRequest.setStatus(TaskStatus.COMPLETED);

        mockMvc.perform(put("/task/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(taskRequest)))
                .andExpect(status().isForbidden());
    }

    @Test
    void deleteTask() throws Exception {
        String token = getToken();
        doNothing().when(taskService).delete(1l);

        mockMvc.perform(delete("/task/{id}", 1l)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void deleteTask_witout_token() throws Exception {
        mockMvc.perform(delete("/task/{id}", 1))
                .andExpect(status().isForbidden());
    }

    private TaskRequest getTaskRequest() {
        return new TaskRequest("New task", "Description", TaskStatus.PENDING);
    }

    private Task getTask() {
        com.api.todolist.entity.User user = new com.api.todolist.entity.User("admin", "123456", true);

        Task task = new Task();
        task.setId(1L);
        task.setName("Task Name");
        task.setDescription("Task description");
        task.setStatus(TaskStatus.PENDING);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        task.setUser(user);
        return task;
    }

    private TaskResponse getTaskResponse() {
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(1L);
        taskResponse.setName("Task Name");
        taskResponse.setDescription("Task description");
        taskResponse.setStatus(TaskStatus.PENDING);
        taskResponse.setUser("user");
        taskResponse.setCreatedAt(LocalDateTime.now());
        taskResponse.setUpdatedAt(LocalDateTime.now());
        return taskResponse;
    }

    private String getToken() {
        return jwtUtil.generateToken(new User("user", "password", new ArrayList<>()));
    }

}