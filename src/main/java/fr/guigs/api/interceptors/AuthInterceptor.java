package fr.guigs.api.interceptors;

import fr.guigs.api.models.User;
import fr.guigs.api.repositories.RoleRepository;
import fr.guigs.api.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import java.util.Enumeration;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public AuthInterceptor(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public static String userId = null;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            if (headerName.equalsIgnoreCase("UserId") || headerName.equalsIgnoreCase("userid")) {
                userId = request.getHeader(headerName);
                System.out.println(userId);
                break;
            }
        }
        if (userId == null) {
            String auth0Id = extractAuth0IdFromToken(request.getHeader("Authorization"));
            System.out.println(auth0Id);
            User user = userRepository.findByAuth0Id(auth0Id);
            if (user == null) {
                user = new User();
                user.setAuth0Id(auth0Id);
                user.setRole(roleRepository.findById(1L).orElseThrow(() -> new RuntimeException("Role not found")));
                userRepository.save(user);
            }
            response.addHeader("UserId", user.getId().toString());
            response.addHeader("Access-Control-Expose-Headers", "UserId");
            response.setStatus(HttpStatus.PARTIAL_CONTENT.value());
            return false;
        }
        return true;
    }

    private String extractAuth0IdFromToken(String token) {
        String[] parts = token.split("\\.");
        if (parts.length != 3) {
            throw new RuntimeException("Invalid token");
        }
        String payload = new String(java.util.Base64.getDecoder().decode(parts[1]));
        JSONObject json = new JSONObject(payload);
        return json.getString("sub");
    }
}