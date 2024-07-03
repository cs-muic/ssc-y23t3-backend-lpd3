package io.muzoo.ssc.Project.Service;


import io.muzoo.ssc.Project.data.User;
import io.muzoo.ssc.Project.data.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(String firstName, String lastName, String email, String password, String passwordConfirm) {
        if (!password.equals(passwordConfirm)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords do not match");
        }
        else return userRepo.save(
                User.of(firstName, lastName, email, passwordEncoder.encode(password))
        );
    }

    public User login(String email, String password) {
        User findEmail = userRepo.findByEmail(email);
        if (findEmail == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        if (!passwordEncoder.matches(password, findEmail.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect password");
        }
        return findEmail;
    }
}

