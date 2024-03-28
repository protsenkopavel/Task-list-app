package net.protsenko.tasklist.service;

import net.protsenko.tasklist.domain.task.TaskImage;

public interface ImageService {

    String upload(TaskImage image);
}
