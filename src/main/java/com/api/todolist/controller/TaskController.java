package com.api.todolist.controller;


import com.api.todolist.entity.TaskStatus;
import com.api.todolist.model.TaskRequest;
import com.api.todolist.model.TaskResponse;
import com.api.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TaskController {

    @Autowired
    public TaskService taskService;

    @GetMapping("/user")
    public ResponseEntity<?> getAllTasksByUser(@RequestParam(required = false) TaskStatus status) throws Exception {
        List<TaskResponse> response = taskService.findByUser(status);
        return response.isEmpty() ? ResponseEntity.notFound().build() :
                ResponseEntity.ok(response);
    }

    @PostMapping("/user")
    public ResponseEntity<?> saveTask(@RequestBody TaskRequest taskRequest) throws Exception {
        TaskResponse response = taskService.save(taskRequest);
        if (response == null) ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.accepted().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable(value = "id") Long taskId) throws Exception {
        taskService.delete(taskId);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable(value = "id") Long taskId,
                                        @RequestBody TaskRequest taskRequest) throws Exception {

        return ResponseEntity.ok(taskService.update(taskId, taskRequest));
    }
}
