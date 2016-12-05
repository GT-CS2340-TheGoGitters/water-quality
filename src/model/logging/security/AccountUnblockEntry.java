package model.logging.security;

import model.Account;

public class AccountUnblockEntry extends SecurityLogEntry {
    protected String unblockedUsername;

    public AccountUnblockEntry(Account actingAccount, String unblockedUsername) {
        super(actingAccount);

        this.unblockedUsername = unblockedUsername;
    }

    public String getLabel() {
        return "Unblocked Account";
    }

    public String getMessage() {
        return "Username: " + unblockedUsername;
    }
}