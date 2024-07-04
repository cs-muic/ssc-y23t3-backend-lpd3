package io.muzoo.ssc.Project.Service;

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
            throw new NoBearerTokenError();

        request.setAttribute("user",authService.getUserFromToken(authorizationHeader.substring(7)));

        return true;
    }
}
