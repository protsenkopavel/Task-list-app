package net.protsenko.tasklist.web.controller;

import lombok.RequiredArgsConstructor;
import net.protsenko.tasklist.domain.task.Task;
import net.protsenko.tasklist.domain.user.User;
import net.protsenko.tasklist.service.TaskService;
import net.protsenko.tasklist.service.UserService;
import net.protsenko.tasklist.web.dto.task.TaskDto;
import net.protsenko.tasklist.web.dto.user.UserDto;
import net.protsenko.tasklist.web.dto.validation.OnCreate;
import net.protsenko.tasklist.web.dto.validation.OnUpdate;
import net.protsenko.tasklist.web.mappers.TaskMapper;
import net.protsenko.tasklist.web.mappers.UserMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    private final TaskService taskService;

    private final UserMapper userMapper;

    private final TaskMapper taskMapper;

    @PutMapping
    public UserDto update(@Validated(OnUpdate.class) @RequestBody UserDto dto) {
        User user = userMapper.toEntity(dto);
        User updatedUser = userService.update(user);
        return userMapper.toDto(updatedUser);
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        User user = userService.getById(id);
        return userMapper.toDto(user);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/{id}/tasks")
    public List<TaskDto> getTasksByUserId(@PathVariable Long id) {
        List<Task> tasks = taskService.getAllByUserId(id);
        return taskMapper.toDto(tasks);
    }

    @PostMapping("/{id}/tasks")
    public TaskDto createTast(@PathVariable Long id, @Validated(OnCreate.class) @RequestBody TaskDto dto) {
        Task task = taskMapper.toEntity(dto);
        Task createdTask = taskService.create(task, id);
        return taskMapper.toDto(createdTask);
    }

}
