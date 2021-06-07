package com.api.todolist.mapper;

import com.api.todolist.entity.Task;
import com.api.todolist.entity.User;
import com.api.todolist.model.TaskRequest;
import com.api.todolist.model.TaskResponse;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public abstract class TaskMapper {

    @NotNull
    public static Task getTask(TaskRequest taskRequest, User user) {
        Task task = new Task();
        task.setName(taskRequest.getName());
        task.setDescription(taskRequest.getDescription());
        task.setStatus(taskRequest.getStatus());
        task.setUser(user);
        return task;
    }

    @NotNull
    public static TaskResponse getTaskResponse(Task responsedb) {
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(responsedb.getId());
        taskResponse.setName(responsedb.getName());
        taskResponse.setDescription(responsedb.getDescription());
        taskResponse.setStatus(responsedb.getStatus());
        taskResponse.setUser(responsedb.getUser().getUsername());
        taskResponse.setCreatedAt(responsedb.getCreatedAt());
        taskResponse.setUpdatedAt(responsedb.getUpdatedAt());
        return taskResponse;
    }

    @NotNull
    public static List<TaskResponse> getTaskResponse(List<Task> tasks) {
        List<TaskResponse> responseList = new ArrayList<>();
        tasks.stream()
                .forEach(t -> responseList.add(getTaskResponse(t)));
        return responseList;
    }
}
