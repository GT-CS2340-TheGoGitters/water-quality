package model.logging.security;

import model.Account;

public class AccountUnbanEntry extends SecurityLogEntry {
    protected String unbannedUsername;

    public AccountUnbanEntry(Account actingAccount, String unbannedUsername) {
        super(actingAccount);

        this.unbannedUsername = unbannedUsername;
    }

    public String getLabel() {
        return "Unbanned Account";
    }

    public String getMessage() {
        return "Username: " + unbannedUsername;
    }
}