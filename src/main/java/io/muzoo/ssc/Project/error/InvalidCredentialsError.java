package io.muzoo.ssc.Project.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidCredentialsError extends ResponseStatusException {
    public InvalidCredentialsError() {
        super(HttpStatus.UNAUTHORIZED, "Invalid credentials");
    }

    public InvalidCredentialsError(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }

    public InvalidCredentialsError(String message, Throwable cause) {
        super(HttpStatus.UNAUTHORIZED, message, cause);
    }
}
