package net.protsenko.tasklist.service.impl;

import net.protsenko.tasklist.config.TestConfig;
import net.protsenko.tasklist.domain.exception.ResourceNotFoundException;
import net.protsenko.tasklist.domain.task.Status;
import net.protsenko.tasklist.domain.task.Task;
import net.protsenko.tasklist.repository.TaskRepository;
import net.protsenko.tasklist.repository.UserRepository;
import net.protsenko.tasklist.service.ImageService;
import net.protsenko.tasklist.service.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@Import(TestConfig.class)
@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest {

    @MockBean
    private TaskRepository taskRepository;

    @MockBean
    private ImageService imageService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AuthenticationManager authenticationManager;

    @Autowired
    private TaskServiceImpl taskService;

    @Test
    void getById() {
        Long id = 1L;
        Task task = new Task();
        task.setId(id);
        Mockito.when(taskRepository.findById(id))
                .thenReturn(Optional.of(task));
        Task testTask = taskService.getById(id);
        Mockito.verify(taskRepository).findById(id);
        Assertions.assertEquals(task, testTask);
    }

    @Test
    void getByIdWithNotExistingId() {
        Long id = 1L;
        Mockito.when(taskRepository.findById(id))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> taskService.getById(id));
        Mockito.verify(taskRepository).findById(id);
    }

    @Test
    void getAllByUserId() {
        Long userId = 1L;
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            tasks.add(new Task());
        }
        Mockito.when(taskRepository.findAllByUserId(userId))
                .thenReturn(tasks);
        List<Task> testTasks = taskService.getAllByUserId(userId);
        Mockito.verify(taskRepository).findAllByUserId(userId);
        Assertions.assertEquals(tasks, testTasks);
    }

    @Test
    void update() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("title");
        task.setDescription("description");
        task.setExpirationDate(LocalDateTime.now());
        task.setStatus(Status.DONE);
        Task testTask = taskService.update(task);
        Mockito.verify(taskRepository).save(task);
        Assertions.assertEquals(task, testTask);
    }

    @Test
    void updateWithNullStatus() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("title");
        task.setDescription("description");
        task.setExpirationDate(LocalDateTime.now());
        Task testTask = taskService.update(task);
        Mockito.verify(taskRepository).save(task);
        Assertions.assertEquals(testTask.getStatus(), Status.TODO);
    }

    @Test
    void create() {
        Long taskId = 1L;
        Long userId = 1L;
        Task task = new Task();
        Mockito.doAnswer(invocationOnMock -> {
            Task savedTask = invocationOnMock.getArgument(0);
            savedTask.setId(taskId);
            return savedTask;
        })
                .when(taskRepository).save(task);
        Task testTask = taskService.create(task, userId);
        Mockito.verify(taskRepository).save(task);
        Assertions.assertNotNull(testTask.getId());
    }

    @Test
    void delete() {
        Long id = 1L;
        taskService.delete(id);
        Mockito.verify(taskRepository).deleteById(id);
    }

}
