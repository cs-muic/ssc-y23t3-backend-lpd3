package io.muzoo.ssc.Project.Service;


import dev.samstevens.totp.code.CodeVerifier;
import io.muzoo.ssc.Project.data.PasswordRecovery;
import io.muzoo.ssc.Project.data.Token;
import io.muzoo.ssc.Project.data.User;
import io.muzoo.ssc.Project.data.UserRepo;
import io.muzoo.ssc.Project.error.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.UUID;

@Service
public class AuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final String accessTokenSecret;
    private final String refreshTokenSecret;
    private final MailService mailService;
    private final CodeVerifier codeVerifier;

    public AuthService(
            UserRepo userRepo,
            PasswordEncoder passwordEncoder,
            @Value("${application.security.access-token-secret}") String accessTokenSecret,
            @Value("${application.security.refresh-token-secret}") String refreshTokenSecret,
            MailService mailService, CodeVerifier codeVerifier) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.accessTokenSecret = accessTokenSecret;
        this.refreshTokenSecret = refreshTokenSecret;
        this.mailService = mailService;
        this.codeVerifier = codeVerifier;
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
        var user = userRepo.findByEmail(email)
                .orElseThrow(InvalidCredentialsError::new);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsError();
        }

        var login = Login.of(user.getId(), accessTokenSecret, refreshTokenSecret, Objects.equals(user.getTfaSecret(),""));
        var refreshJwt = login.getRefreshToken();

        user.addToken(new Token(refreshJwt.getToken(),refreshJwt.getIssuedAt(), refreshJwt.getExpiration()));
        userRepo.save(user);

        return login;
    }

    public User getUserFromToken(String token) {
        return userRepo.findById(Jwt.from(token, accessTokenSecret).getUserId())
                .orElseThrow(UserNotFoundError::new);
    }

    public Login refreshAccess(String refreshToken) {
        var refreshJwt = Jwt.from(refreshToken, refreshTokenSecret);

        var user = userRepo.findByIdAndTokenRefreshTokenAndTokensExpiredAtGreaterThan(refreshJwt.getUserId(),refreshJwt.getToken(),refreshJwt.getExpiration())
                .orElseThrow(UnauthenticatedError::new);

        return Login.of(refreshJwt.getUserId(), accessTokenSecret, refreshJwt, false);
    }

    public Boolean logout(String refreshToken){
        var refreshJwt = Jwt.from(refreshToken, refreshTokenSecret);
        var user = userRepo.findById(refreshJwt.getUserId())
                .orElseThrow(UnauthenticatedError::new);
        var tokenIsRemoved= user.removeTokenIf(token -> Objects.equals(token.refreshToken(), refreshToken));

        if (tokenIsRemoved)
            userRepo.save(user);

        return tokenIsRemoved;
    }

    public void forgot(String email, String originalUrl) {
        var token = UUID.randomUUID().toString().replace("-","");
        var user = userRepo.findByEmail(email)
                .orElseThrow(UserNotFoundError::new);
        user.addPassswordRecovery(new PasswordRecovery(token));

        mailService.sendForgotMessage(email, token, originalUrl);

        userRepo.save(user);
    }

    public void reset(String token, String password, String passwordConfirm){
        if (!Objects.equals(password,passwordConfirm))
            throw new PasswordsDoNotMatchError();
        var user = userRepo.findByPasswordRecoveriesToken(token)
                .orElseThrow(InvalidLinkError::new);

        user.setPassword(passwordEncoder.encode(password));
        user.removePasswordRecoveryIf(passwordRecovery -> Objects.equals(passwordRecovery.token(),token));

        userRepo.save(user);
    }

    public Login twoFactorLogin(Long id, String secret, String code) {
        var user = userRepo.findById(id)
                .orElseThrow(InvalidCredentialsError::new);

        var tfaSecret = !Objects.equals(user.getTfaSecret(),"") ? user.getTfaSecret():secret;

        if(codeVerifier.isValidCode(tfaSecret,code))
            throw new InvalidCredentialsError();

        if (Objects.equals(user.getTfaSecret(),"")){
            user.setTfaSecret(secret);
            userRepo.save(user);
        }
        var login = Login.of(user.getId(), accessTokenSecret, refreshTokenSecret,false);
        var refreshJwt = login.getRefreshToken();

        user.addToken(new Token(refreshJwt.getToken(),refreshJwt.getIssuedAt(), refreshJwt.getExpiration()));
        userRepo.save(user);

        return login;
    }
}

