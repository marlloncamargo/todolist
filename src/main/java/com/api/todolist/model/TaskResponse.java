package com.api.todolist.model;

import com.api.todolist.entity.TaskStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class TaskResponse implements Serializable {

    private Long id;
    private String name;
    private String description;
    private TaskStatus status;
    private String user;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
}
