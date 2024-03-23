package net.protsenko.tasklist.web.controller;

import lombok.RequiredArgsConstructor;
import net.protsenko.tasklist.domain.user.User;
import net.protsenko.tasklist.service.AuthService;
import net.protsenko.tasklist.service.UserService;
import net.protsenko.tasklist.web.dto.auth.JwtRequest;
import net.protsenko.tasklist.web.dto.auth.JwtResponse;
import net.protsenko.tasklist.web.dto.user.UserDto;
import net.protsenko.tasklist.web.dto.validation.OnCreate;
import net.protsenko.tasklist.web.mappers.UserMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final AuthService authService;

    private final UserService userService;

    private final UserMapper userMapper;

    @PostMapping("/login")
    public JwtResponse login(@Validated @RequestBody JwtRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public UserDto register(@Validated(OnCreate.class) @RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User createdUser = userService.create(user);
        return userMapper.toDto(createdUser);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody String refreshToken) {
        return authService.refresh(refreshToken);
    }

}
