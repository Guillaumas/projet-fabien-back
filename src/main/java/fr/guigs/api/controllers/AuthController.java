package fr.guigs.api.controllers;


import fr.guigs.api.exceptions.UserAlreadyExistsException;
import fr.guigs.api.interceptors.AuthInterceptor;
import fr.guigs.api.models.User;
import fr.guigs.api.repositories.RoleRepository;
import fr.guigs.api.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthInterceptor authInterceptor;


    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthInterceptor authInterceptor) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authInterceptor = authInterceptor;
    }


    @PostMapping("/register")
    public String register(@RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException("Username already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleRepository.findById(1L).orElseThrow(() -> new RuntimeException("Role not found")));
        userRepository.save(user);
        return "User registered successfully";
    }


    @GetMapping("/userId")
    public Long getUserIdRoute(@RequestHeader("Authorization") String bearerToken) {
        String auth0Id = authInterceptor.extractAuth0IdFromToken(bearerToken);
        User user = userRepository.findByAuth0Id(auth0Id);
        System.out.println("ici");
        return user.getId();
    }
}
