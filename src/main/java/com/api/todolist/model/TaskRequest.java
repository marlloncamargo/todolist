package com.api.todolist.model;

import com.api.todolist.entity.TaskStatus;
import lombok.Data;

import java.io.Serializable;

@Data
public class TaskRequest implements Serializable {
    private String name;
    private String description;
    private TaskStatus status;
}
