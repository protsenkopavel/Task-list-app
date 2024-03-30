package net.protsenko.tasklist.config;

import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import net.protsenko.tasklist.repository.TaskRepository;
import net.protsenko.tasklist.repository.UserRepository;
import net.protsenko.tasklist.service.AuthService;
import net.protsenko.tasklist.service.ImageService;
import net.protsenko.tasklist.service.TaskService;
import net.protsenko.tasklist.service.UserService;
import net.protsenko.tasklist.service.impl.AuthServiceImpl;
import net.protsenko.tasklist.service.impl.ImageServiceIml;
import net.protsenko.tasklist.service.impl.TaskServiceImpl;
import net.protsenko.tasklist.service.impl.UserServiceImpl;
import net.protsenko.tasklist.service.props.JwtProperties;
import net.protsenko.tasklist.service.props.MinioProperties;
import net.protsenko.tasklist.web.security.JwtTokenProvider;
import net.protsenko.tasklist.web.security.JwtUserDetailsService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@TestConfiguration
@RequiredArgsConstructor
public class TestConfig {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final AuthenticationManager authenticationManager;

    @Bean
    @Primary
    public BCryptPasswordEncoder testPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    JwtProperties jwtProperties() {
        JwtProperties jwtProperties = new JwtProperties();
        jwtProperties.setSecret("ZGZkc3lnZnVkc2Zkc3ZpZHNpbGpkc2ZqbGRzaWxmbGRzZmRzaGxmaGxz");
        return jwtProperties;
    }

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        return new JwtUserDetailsService(userService());
    }

    @Bean
    public MinioClient minioClient() {
        return Mockito.mock(MinioClient.class);
    }

    @Bean
    public MinioProperties minioProperties() {
        MinioProperties minioProperties = new MinioProperties();
        minioProperties.setBucket("images");
        return minioProperties;
    }

    @Bean
    @Primary
    public ImageService imageService() {
        return new ImageServiceIml(minioClient(), minioProperties());
    }

    @Bean
    public JwtTokenProvider tokenProvider() {
        return new JwtTokenProvider(jwtProperties(),
                userDetailsService(),
                userService());
    }

    @Bean
    @Primary
    public UserService userService() {
        return new UserServiceImpl(userRepository, testPasswordEncoder());
    }

    @Bean
    @Primary
    public TaskService taskService() {
        return new TaskServiceImpl(taskRepository, userService(), imageService());
    }

    @Bean
    @Primary
    public AuthService authService() {
        return new AuthServiceImpl(authenticationManager, userService(), tokenProvider());
    }


}
