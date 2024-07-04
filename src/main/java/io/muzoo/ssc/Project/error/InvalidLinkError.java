package io.muzoo.ssc.Project.error;

public class InvalidLinkError extends RuntimeException {
    public InvalidLinkError() {
        super("Invalid link.");
    }
}
