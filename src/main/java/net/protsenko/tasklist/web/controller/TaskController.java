package net.protsenko.tasklist.web.controller;

import lombok.RequiredArgsConstructor;
import net.protsenko.tasklist.domain.task.Task;
import net.protsenko.tasklist.service.TaskService;
import net.protsenko.tasklist.web.dto.task.TaskDto;
import net.protsenko.tasklist.web.dto.validation.OnUpdate;
import net.protsenko.tasklist.web.mappers.TaskMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@Validated
public class TaskController {

    private final TaskService taskService;

    private final TaskMapper taskMapper;

    @PutMapping
    public TaskDto update(@Validated(OnUpdate.class) @RequestBody TaskDto dto) {
        Task task = taskMapper.toEntity(dto);
        Task updatedTask = taskService.update(task);

        return taskMapper.toDto(updatedTask);
    }

    @GetMapping("/{id}")
    public TaskDto getById(@PathVariable Long id) {
        Task task = taskService.getById(id);

        return taskMapper.toDto(task);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        taskService.delete(id);
    }

}
