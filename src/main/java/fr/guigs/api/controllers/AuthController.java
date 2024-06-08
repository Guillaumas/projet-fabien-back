package fr.guigs.api.controllers;


import fr.guigs.api.configs.JWTUtil;
import fr.guigs.api.exceptions.BadCredentialsException;
import fr.guigs.api.exceptions.UserAlreadyExistsException;
import fr.guigs.api.models.User;
import fr.guigs.api.repositories.RoleRepository;
import fr.guigs.api.repositories.UserRepository;
import fr.guigs.api.services.TokenValidationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final JWTUtil jwtUtil;

    private final TokenValidationService tokenValidationService;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTUtil jwtUtil, TokenValidationService tokenValidationService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.tokenValidationService = tokenValidationService;
    }


    @PostMapping("/register")
    public String register(@RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException("Username already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(roleRepository.findById(1L).orElseThrow(() -> new RuntimeException("Role not found"))));
        userRepository.save(user);
        return "User registered successfully";
    }


    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtUtil.generateJwtToken(user.getUsername());
            Map<String, String> response = new HashMap<>();
            response.put("token", jwt);
            return response;
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("/checkToken")
    public Map<String, Boolean> checkToken(@RequestBody Map<String, String> body) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("valid", tokenValidationService.isTokenValid(body.get("token")));
        return response;
    }

    @PostMapping("/refreshToken")
    public Map<String, String> refreshToken(@RequestBody Map<String, String> body) {
        String token = body.get("token");
        String username = jwtUtil.getUsernameFromJwtToken(token);
        String newToken = jwtUtil.generateJwtToken(username);
        Map<String, String> response = new HashMap<>();
        response.put("token", newToken);
        return response;
    }
}
