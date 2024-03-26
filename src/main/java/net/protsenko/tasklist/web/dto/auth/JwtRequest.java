package net.protsenko.tasklist.web.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Request for login")
public class JwtRequest {

    @Schema(description = "email", example = "somemail@mail.ru")
    @NotNull(message = "Username must not be null.")
    private String username;

    @Schema(description = "password", example = "12345")
    @NotNull(message = "Password must not be null")
    private String password;

}
