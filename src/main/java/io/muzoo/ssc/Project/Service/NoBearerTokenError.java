package io.muzoo.ssc.Project.Service;

public class NoBearerTokenError extends RuntimeException {
    public NoBearerTokenError() {
        super("Authorization header is missing or does not contain a Bearer token");
    }

    public NoBearerTokenError(String message) {
        super(message);
    }

    public NoBearerTokenError(String message, Throwable cause) {
        super(message, cause);
    }

    public NoBearerTokenError(Throwable cause) {
        super(cause);
    }
}

