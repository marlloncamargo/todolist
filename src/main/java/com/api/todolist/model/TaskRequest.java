package com.api.todolist.model;

import com.api.todolist.entity.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest implements Serializable {
    private String name;
    private String description;
    private TaskStatus status;
}
