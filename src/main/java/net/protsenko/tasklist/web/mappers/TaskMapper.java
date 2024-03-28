package net.protsenko.tasklist.web.mappers;

import net.protsenko.tasklist.domain.task.Task;
import net.protsenko.tasklist.web.dto.task.TaskDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper extends Mappable<Task, TaskDto> {

}
