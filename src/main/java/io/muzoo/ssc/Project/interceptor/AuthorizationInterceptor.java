package io.muzoo.ssc.Project.interceptor;

import io.muzoo.ssc.Project.Service.AuthService;
import io.muzoo.ssc.Project.error.NoBearerTokenError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    private final AuthService authService;
    public AuthorizationInterceptor(AuthService authService){
        this.authService = authService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String authorizationHeader = request.getHeader("Authorization");

        if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer "))

        request.setAttribute("user",authService.getUserFromToken(authorizationHeader.substring(7)));

        return true;
    }
}
