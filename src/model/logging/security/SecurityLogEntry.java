package model.logging.security;

import model.Account;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public abstract class SecurityLogEntry implements Serializable{
    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z", Locale.US);

    private final Account actingAccount;
    private final Date time;

    @SuppressWarnings("unused")
    // This will be used in the security log viewer
    public Account getActingAccount() {
        return actingAccount;
    }

    @SuppressWarnings("unused")
    // This will be used in the security log viewer
    public Date getTime() {
        return time;
    }

    SecurityLogEntry(Account actingAccount) {
        this.actingAccount = actingAccount;
        this.time = new Date();
    }

    protected abstract String getLabel();

    protected abstract String getMessage();

    @Override
    public String toString() {
        String str = "[" + dateFormat.format(time) + "] " + getLabel();

        String message = getMessage();
        if (message != null && !message.isEmpty()) {
            str += ": " + getMessage();
        }

        return str;
    }
}
