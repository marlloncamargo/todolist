package com.api.todolist.service.impl;

import com.api.todolist.entity.Task;
import com.api.todolist.entity.TaskStatus;
import com.api.todolist.entity.User;
import com.api.todolist.mapper.TaskMapper;
import com.api.todolist.model.TaskRequest;
import com.api.todolist.model.TaskResponse;
import com.api.todolist.repository.TaskRepository;
import com.api.todolist.repository.UserRepository;
import com.api.todolist.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<TaskResponse> findByUser(TaskStatus status, String username) throws Exception {
        User user = checkCurrentUser(username);

        Sort sort = Sort.by(Sort.Direction.ASC, "status");
        if(user.getAdmin().equals(Boolean.TRUE)){
            log.info("Admin user found. Return all tasks");
            return TaskMapper.getTaskResponse(filterList(repository.findAll(sort), status));
        }
        return TaskMapper.getTaskResponse(filterList(repository.findByUser(user, sort), status));
    }

    @Override
    public TaskResponse save(TaskRequest taskRequest, String username) throws Exception {
        User user = checkCurrentUser(username);

        if (checkStatus(taskRequest.getStatus())){
            log.warn("Status is undefined. Set PENDING as default");
            taskRequest.setStatus(TaskStatus.PENDING);
        }

        Task task = TaskMapper.getTask(taskRequest, user);
        log.info("Save task into database");
        return TaskMapper.getTaskResponse(repository.save(task));
    }

    @Override
    public void delete(Long taskId) throws Exception {
        Task task = repository.findById(taskId)
                .orElseThrow(() -> new Exception("Task not found for this id: " + taskId));
        log.info("Delete task into database");
        repository.delete(task);
    }

    @Override
    public TaskResponse update(Long taskId, TaskRequest taskRequest) throws Exception {
        Task taskFound = repository.findById(taskId)
                .orElseThrow(() -> new Exception("Task not found for this id: " + taskId));

        taskFound.setName(taskRequest.getName());
        taskFound.setDescription(taskRequest.getDescription());
        taskFound.setStatus(taskRequest.getStatus());

        log.info("Update task into database");
        return TaskMapper.getTaskResponse(repository.save(taskFound));
    }

    private List<Task> filterList(List<Task> list, TaskStatus status){
        if(status == TaskStatus.PENDING || status == TaskStatus.COMPLETED){
            return list.stream()
                    .filter(t -> t.getStatus() == status)
                    .collect(Collectors.toList());
        }
        return list;
    }

    private boolean checkStatus(TaskStatus status){
        return status != TaskStatus.COMPLETED && status != TaskStatus.PENDING;
    }

    private User checkCurrentUser(String currentPrincipalName) throws Exception {
        log.info("Retrive user information is logged in");
        return userRepository.findByUsername(currentPrincipalName)
                .orElseThrow(() -> new Exception("User not found for this username: " + currentPrincipalName));
    }
}
