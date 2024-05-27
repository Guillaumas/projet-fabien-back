package fr.guigs.api.services;

import fr.guigs.api.configs.JWTUtil;
import org.springframework.stereotype.Service;

@Service
public class TokenValidationService {

    private final JWTUtil jwtUtil;

    public TokenValidationService(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public boolean isTokenValid(String token) {
        return jwtUtil.validateJwtToken(token);
    }
}