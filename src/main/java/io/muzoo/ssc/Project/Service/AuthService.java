package io.muzoo.ssc.Project.Service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.muzoo.ssc.Project.data.User;
import io.muzoo.ssc.Project.data.UserRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final String accessTokenSecret;
    private final String refreshTokenSecret;

    public AuthService(
            UserRepo userRepo,
            PasswordEncoder passwordEncoder,
            @Value("${application.security.access-token-secret}") String accessTokenSecret,
            @Value("${application.security.refresh-token-secret}") String refreshTokenSecret) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.accessTokenSecret = accessTokenSecret;
        this.refreshTokenSecret = refreshTokenSecret;
    }

    public User register(String firstName, String lastName, String email, String password, String passwordConfirm) {
        if (!password.equals(passwordConfirm)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords do not match");
        }
        else return userRepo.save(
                User.of(firstName, lastName, email, passwordEncoder.encode(password))
        );
    }

    public Login login(String email, String password) {
        User findEmail = userRepo.findByEmail(email);
        if (findEmail == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        if (!passwordEncoder.matches(password, findEmail.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect password");
        }
        return Login.of(findEmail.getId(),accessTokenSecret,refreshTokenSecret);
    }

    public User getUserFromToken(String token) {
        return userRepo.findById(Token.from(token, accessTokenSecret))
                .orElseThrow(UserNotFoundError::new);
    }

    public Login refreshAccess(String refreshToken) {
        var userId = Token.from(refreshToken, refreshTokenSecret);

        return Login.of(userId, accessTokenSecret, Token.of(refreshToken));
    }
}

