package net.protsenko.tasklist.web.mappers;

import net.protsenko.tasklist.domain.user.User;
import net.protsenko.tasklist.web.dto.user.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserDto> {
}
