package br.tomorrow.tcrm.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        // Verifica se a exceção está relacionada a token JWT
        if (isJwtRelatedException(authException)) {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            Map<String, Object> body = new HashMap<>();
            body.put("message", "Token inválido ou expirado");
            body.put("error", "Unauthorized");
            body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
            body.put("path", request.getRequestURI());

            new ObjectMapper().writeValue(response.getOutputStream(), body);
        }
    }

    private boolean isJwtRelatedException(AuthenticationException authException) {
        // Verifica o tipo da exceção ou a mensagem para identificar problemas com JWT
        return authException.getCause() instanceof io.jsonwebtoken.JwtException ||
                authException.getMessage().contains("JWT") ||
                authException.getMessage().contains("token");
    }
}