package com.api.todolist.controller;


import com.api.todolist.model.Task;
import com.api.todolist.model.TaskStatus;
import com.api.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TaksController{

    @Autowired
    public TaskService taskService;

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getAllTasksByUser(@PathVariable(value = "id") Long userId,
                                               @RequestParam(required = false) TaskStatus status) throws Exception {
        List<Task> response = taskService.findByUser(userId, status);
        return response.isEmpty() ? ResponseEntity.notFound().build() :
                ResponseEntity.ok(response);
    }

    @PostMapping("/user/{id}")
    public ResponseEntity<?> saveTask(@RequestBody Task task, @PathVariable(value = "id") Long userId) throws Exception {
        Task response = taskService.save(task, userId);
        if (response == null) ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        return ResponseEntity.accepted().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable(value = "id") Long taskId) throws Exception {
        taskService.delete(taskId);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable(value = "id") Long taskId,
                                        @RequestBody Task task) throws Exception {

        return ResponseEntity.ok(taskService.update(taskId, task));
    }
}
