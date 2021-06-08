package com.api.todolist.service;

import com.api.todolist.entity.Task;
import com.api.todolist.entity.TaskStatus;
import com.api.todolist.entity.User;
import com.api.todolist.model.TaskRequest;
import com.api.todolist.repository.TaskRepository;
import com.api.todolist.repository.UserRepository;
import com.api.todolist.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @InjectMocks
    TaskService service = new TaskServiceImpl();

    @Mock
    TaskRepository repository;

    @Mock
    UserRepository userRepository;

    @Test
    void save() throws Exception {
        User user = new User("admin", "123456", true);

        Task task = new Task();
        task.setName("Task Name");
        task.setDescription("Task description");
        task.setStatus(TaskStatus.PENDING);
        task.setUser(user);

        given(userRepository.findByUsername(anyString())).willReturn(java.util.Optional.of(user));
        when(repository.save(any())).thenReturn(task);

        service.save(new TaskRequest(task.getName(),
                task.getDescription(),
                task.getStatus()), user.getUsername());

        verify(repository, times(1)).save(task);
    }

    @Test
    void update() throws Exception {
        User user = new User("admin", "123456", true);

        Task task = new Task();
        task.setId(1L);
        task.setName("Task Name");
        task.setDescription("Task description");
        task.setStatus(TaskStatus.PENDING);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        task.setUser(user);

        given(repository.findById(anyLong())).willReturn(java.util.Optional.of(task));
        when(repository.save(any())).thenReturn(task);

        service.update(task.getId(), new TaskRequest(task.getName(), task.getDescription(), TaskStatus.COMPLETED));

        verify(repository, times(1)).save(task);
    }

    @Test
    void findByUser() throws Exception {
        User user = new User("admin", "123456", true);

        Task task = new Task();
        task.setId(1L);
        task.setName("Task Name");
        task.setDescription("Task description");
        task.setStatus(TaskStatus.PENDING);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        task.setUser(user);

        Sort sort = Sort.by(Sort.Direction.ASC, "status");

        given(userRepository.findByUsername(anyString())).willReturn(java.util.Optional.of(user));
        when(repository.findAll(sort)).thenReturn(List.of(task));

        service.findByUser(task.getStatus(), user.getUsername());

        verify(repository, times(1))
                .findAll(sort);

    }

    @Test
    void delete() throws Exception {
        User user = new User("admin", "123456", true);

        Task task = new Task();
        task.setId(1L);
        task.setName("Task Name");
        task.setDescription("Task description");
        task.setStatus(TaskStatus.PENDING);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        task.setUser(user);

        given(repository.findById(anyLong())).willReturn(java.util.Optional.of(task));
        service.delete(task.getId());

        verify(repository, times(1)).delete(eq(task));
    }

}