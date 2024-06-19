package fr.guigs.api.filters;

import fr.guigs.api.models.User;
import fr.guigs.api.repositories.RoleRepository;
import fr.guigs.api.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = request.getHeader("userId");
        if (userId == null) {
            String auth0Id = extractAuth0IdFromToken(request.getHeader("Authorization"));
            User user = userRepository.findByAuth0Id(auth0Id);
            if (user == null) {
                user = new User();
                user.setAuth0Id(auth0Id);
                user.setRole(roleRepository.findById(1L).orElseThrow(() -> new RuntimeException("Role not found")));
                userRepository.save(user);
            }
            response.addHeader("userId", user.getId().toString());
            response.setStatus(HttpStatus.PARTIAL_CONTENT.value()); // 206 Partial Content
            return false;
        }
        return true;
    }

    private String extractAuth0IdFromToken(String token) {
        //implemente cette methode
        return null;
    }
}