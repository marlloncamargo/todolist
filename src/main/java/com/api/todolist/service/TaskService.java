package com.api.todolist.service;

import com.api.todolist.entity.TaskStatus;
import com.api.todolist.model.TaskRequest;
import com.api.todolist.model.TaskResponse;

import java.util.List;

public interface TaskService {
    /**
    *
     */
    List<TaskResponse> findByUser(TaskStatus status, String username) throws Exception;

    /**
     *
     */
    TaskResponse save(TaskRequest task, String username) throws Exception;

    /**
     *
     */
    void delete(Long taskId) throws Exception;

    /**
     *
     */
    TaskResponse update(Long taskId, TaskRequest taskRequest) throws Exception;
}
