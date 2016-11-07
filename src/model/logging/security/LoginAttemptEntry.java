package model.logging.security;

import model.Account;

public class LoginAttemptEntry extends SecurityLogEntry {
    public enum SuccessStatus {
        UNKNOWN_USER,
        INCORRECT_PASSWORD,
        LOCKED,
        SUCCESS
    }

    private SuccessStatus successStatus;

    private String enteredUsername;

    public LoginAttemptEntry(Account account, String enteredUsername, SuccessStatus status) {
        super(account);

        this.enteredUsername = enteredUsername;
        this.successStatus = status;
    }

    public SuccessStatus getSuccessStatus() {
        return successStatus;
    }

    public String getEnteredUsername() {
        return enteredUsername;
    }

    public String getLabel() {
        return "Login Attempt";
    }

    public String getMessage() {
        return enteredUsername + "; status: " + successStatus;
    }
}
