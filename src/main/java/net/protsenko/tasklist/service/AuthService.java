package net.protsenko.tasklist.service;

import net.protsenko.tasklist.web.dto.auth.JwtRequest;
import net.protsenko.tasklist.web.dto.auth.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken);

}
