package io.muzoo.ssc.Project;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.muzoo.ssc.Project.Service.AuthService;
import io.muzoo.ssc.Project.data.User;
import io.muzoo.ssc.Project.data.UserRepo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;


@RestController
@RequestMapping(value="/api")
public class AuthController {
    private final AuthService authService;

    public AuthController(UserRepo userRepo, AuthService authService) {
        this.authService = authService;
    }

    record RegisterRequest(@JsonProperty("first_name") String firstName,
                           @JsonProperty("last_name") String lastName,
                           String email,
                           String password,
                           @JsonProperty("password_confirm") String passwordConfirm
    ){ }

    record RegisterResponse(Long id, @JsonProperty("first_name") String firstName,
                            @JsonProperty("last_name") String lastName,
                            String email){}


    @PostMapping(value = "/register")
    public RegisterResponse register(@RequestBody RegisterRequest registerRequest) {
        User saveUser = authService.register(
                registerRequest.firstName(),
                registerRequest.lastName(),
                registerRequest.email(),
                registerRequest.password(),
                registerRequest.passwordConfirm()
        );
        return new RegisterResponse(
                saveUser.getId(),
                saveUser.getFirstName(),
                saveUser.getLastName(),
                saveUser.getEmail()
        );
    }

    record LoginRequest(String email,String password){}
    record LoginResponse(String token){}

    @PostMapping(value = "/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        var login = authService.login(loginRequest.email(), loginRequest.password());
        Cookie cookie = new Cookie("refresh_token",login.getRefreshToken().getToken());
        cookie.setMaxAge(3600);
        cookie.setHttpOnly(true);
        cookie.setPath("/api");

        response.addCookie(cookie);
        return new LoginResponse(login.getAccessToken().getToken());
    }
}
