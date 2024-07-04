package io.muzoo.ssc.Project.Service;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundError extends ResponseStatusException {
    public UserNotFoundError() {
        super(HttpStatus.NOT_FOUND, "User not found");
    }

    public UserNotFoundError(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

    public UserNotFoundError(String message, Throwable cause) {
        super(HttpStatus.NOT_FOUND, message, cause);
    }
}
