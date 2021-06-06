package com.api.todolist.service;

import com.api.todolist.model.Task;
import com.api.todolist.model.TaskStatus;
import com.api.todolist.model.User;

import java.util.List;

public interface TaskService {
    /**
    *
     */
    List<Task> findByUser(Long userId, TaskStatus status) throws Exception;

    /**
     *
     */
    Task save(Task task, Long userId) throws Exception;

    /**
     *
     */
    void delete(Long taskId) throws Exception;

    /**
     *
     */
    Task update(Long taskId, Task task) throws Exception;
}
