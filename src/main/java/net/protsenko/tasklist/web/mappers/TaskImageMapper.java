package net.protsenko.tasklist.web.mappers;

import net.protsenko.tasklist.domain.task.Task;
import net.protsenko.tasklist.domain.task.TaskImage;
import net.protsenko.tasklist.web.dto.task.TaskDto;
import net.protsenko.tasklist.web.dto.task.TaskImageDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskImageMapper extends Mappable<TaskImage, TaskImageDto> {
}
