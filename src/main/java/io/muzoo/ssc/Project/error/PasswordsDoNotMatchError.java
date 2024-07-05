package io.muzoo.ssc.Project.error;

public class PasswordsDoNotMatchError extends RuntimeException {
    public PasswordsDoNotMatchError() {
        super("Passwords do not match.");
    }
}

