package net.protsenko.tasklist.service.impl;

import net.protsenko.tasklist.service.AuthService;
import net.protsenko.tasklist.web.dto.auth.JwtRequest;
import net.protsenko.tasklist.web.dto.auth.JwtResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public JwtResponse login(JwtRequest loginRequest) {
        return null;
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return null;
    }

}
