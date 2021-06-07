package com.api.todolist.service;

import com.api.todolist.entity.Task;
import com.api.todolist.entity.TaskStatus;
import com.api.todolist.model.TaskRequest;
import com.api.todolist.model.TaskResponse;

import java.util.List;

public interface TaskService {
    /**
    *
     */
    List<TaskResponse> findByUser(TaskStatus status) throws Exception;

    /**
     *
     */
    TaskResponse save(TaskRequest task) throws Exception;

    /**
     *
     */
    void delete(Long taskId) throws Exception;

    /**
     *
     */
    Task update(Long taskId, Task task) throws Exception;
}
