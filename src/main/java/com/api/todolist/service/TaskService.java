package com.api.todolist.service;

import com.api.todolist.entity.Task;
import com.api.todolist.entity.TaskStatus;

import java.util.List;

public interface TaskService {
    /**
    *
     */
    List<Task> findByUser(TaskStatus status) throws Exception;

    /**
     *
     */
    Task save(Task task) throws Exception;

    /**
     *
     */
    void delete(Long taskId) throws Exception;

    /**
     *
     */
    Task update(Long taskId, Task task) throws Exception;
}
