package br.tomorrow.tcrm.configuration;

import br.tomorrow.tcrm.user.UserEntity;
import br.tomorrow.tcrm.user.UserService;
import br.tomorrow.tcrm.user.exceptions.UserNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    ApplicationContext context;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer")) {
            String jwt = Arrays.stream(authHeader.strip().split(" ")).toList().getLast();
            Optional<UUID> userId = Optional.ofNullable(jwtUtil.getIdFromToken(jwt));

            if (userId.isPresent() && SecurityContextHolder.getContext().getAuthentication() == null){
                UserEntity currentUser = context.getBean(UserService.class).findById(userId.orElseThrow(UserNotFoundException::new));
                if(jwtUtil.validateToken(jwt)){
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(currentUser, null, List.of());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
                else{
                    throw new ExpiratedToken();
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
