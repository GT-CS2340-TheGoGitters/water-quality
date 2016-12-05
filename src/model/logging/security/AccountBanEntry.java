package model.logging.security;

import model.Account;

public class AccountBanEntry extends SecurityLogEntry {
    protected String bannedUsername;

    public AccountBanEntry(Account actingAccount, String bannedUsername) {
        super(actingAccount);

        this.bannedUsername = bannedUsername;
    }

    public String getLabel() {
        return "Banned Account";
    }

    public String getMessage() {
        return "Username: " + bannedUsername;
    }
}