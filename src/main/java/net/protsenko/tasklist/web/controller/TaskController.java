package net.protsenko.tasklist.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.protsenko.tasklist.domain.exception.ExceptionBody;
import net.protsenko.tasklist.domain.exception.ImageUploadException;
import net.protsenko.tasklist.domain.task.Task;
import net.protsenko.tasklist.domain.task.TaskImage;
import net.protsenko.tasklist.service.TaskService;
import net.protsenko.tasklist.web.dto.task.TaskDto;
import net.protsenko.tasklist.web.dto.task.TaskImageDto;
import net.protsenko.tasklist.web.dto.validation.OnUpdate;
import net.protsenko.tasklist.web.mappers.TaskImageMapper;
import net.protsenko.tasklist.web.mappers.TaskMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.image.ImagingOpException;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@Validated
@Tag(name = "Task controller", description = "Task API")
public class TaskController {

    private final TaskService taskService;

    private final TaskMapper taskMapper;
    private final TaskImageMapper taskImageMapper;

    @PutMapping
    @Operation(summary = "Update task")
    @PreAuthorize("canAccessTask(#dto.id)")
    public TaskDto update(@Validated(OnUpdate.class) @RequestBody TaskDto dto) {
        Task task = taskMapper.toEntity(dto);
        Task updatedTask = taskService.update(task);

        return taskMapper.toDto(updatedTask);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get task by id")
    @PreAuthorize("canAccessTask(#id)")
    public TaskDto getById(@PathVariable Long id) {
        Task task = taskService.getById(id);

        return taskMapper.toDto(task);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete task by id")
    @PreAuthorize("canAccessTask(#id)")
    public void deleteById(@PathVariable Long id) {
        taskService.delete(id);
    }

    @ExceptionHandler(ImagingOpException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleImageUpload(ImageUploadException e) {
        return new ExceptionBody(e.getMessage());
    }

    @PostMapping("/{id}/image")
    @Operation(summary = "Upload image to task")
    @PreAuthorize("canAccessTask(#id)")
    public void uploadImage(@PathVariable Long id,
                            @Validated @ModelAttribute TaskImageDto imageDto) {
        TaskImage image = taskImageMapper.toEntity(imageDto);
        taskService.uploadImage(id, image);
    }

}
