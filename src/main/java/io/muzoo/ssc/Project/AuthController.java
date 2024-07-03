package io.muzoo.ssc.Project;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api")
public class AuthController {
    private final UserRepo userRepo;

    public AuthController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping(value="/hello")
    public String hello() {
        return "Hello!";
    }

    record RegisterRequest(@JsonProperty("first_name") String firstName,
                           @JsonProperty("last_name") String lastName,
                           String email,
                           String password,
                           @JsonProperty("password_confirm") String passwordConfirm
    ){}

    record RegisterResponse(Long id, @JsonProperty("first_name") String firstName,
                            @JsonProperty("last_name") String lastName,
                            String email){}

    @PostMapping(value = "/register")
    public RegisterResponse register(@RequestBody RegisterRequest registerRequest) {
        User saveUser = userRepo.save(
                User.of(
                        registerRequest.firstName(),
                        registerRequest.lastName(),
                        registerRequest.email(),
                        registerRequest.password()
                )
        );
        return new RegisterResponse(
                saveUser.getId(),
                saveUser.getFirstName(),
                saveUser.getLastName(),
                saveUser.getEmail()
        );
    }
}
