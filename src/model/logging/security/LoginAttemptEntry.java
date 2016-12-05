package model.logging.security;

import model.Account;

public class LoginAttemptEntry extends SecurityLogEntry {
    public enum SuccessStatus {
        UNKNOWN_USER,
        INCORRECT_PASSWORD,
        LOCKED,
        SUCCESS
    }

    private final SuccessStatus successStatus;

    private final String enteredUsername;

    public LoginAttemptEntry(Account account, String enteredUsername, SuccessStatus status) {
        super(account);

        this.enteredUsername = enteredUsername;
        this.successStatus = status;
    }

    @SuppressWarnings("unused")
    // This may be used in the security log viewer
    public SuccessStatus getSuccessStatus() {
        return successStatus;
    }

    @SuppressWarnings("unused")
    // This may be used in the security log viewer
    public String getEnteredUsername() {
        return enteredUsername;
    }

    public String getLabel() {
        return "Login Attempt";
    }

    public String getMessage() {
        return "Username: " + enteredUsername + "; status: " + successStatus;
    }
}
