package net.protsenko.tasklist.service;

import net.protsenko.tasklist.domain.task.Task;
import net.protsenko.tasklist.domain.task.TaskImage;

import java.time.Duration;
import java.util.List;

public interface TaskService {

    Task getById(Long id);

    List<Task> getAllByUserId(Long id);

    List<Task> getAllSoonTasks(Duration duration);

    Task update(Task task);

    Task create(Task task, Long userId);

    void delete(Long id);

    void uploadImage(Long id, TaskImage image);

}
