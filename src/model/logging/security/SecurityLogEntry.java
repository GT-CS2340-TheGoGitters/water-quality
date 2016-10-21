package model.logging.security;

import model.Account;
import model.AccountType;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Joshua on 10/21/16.
 */
public abstract class SecurityLogEntry {
    protected static final DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z", Locale.US);

    protected Account actingAccount;
    protected Date time;

    public Account getActingAccount() {
        return actingAccount;
    }

    public Date getTime() {
        return time;
    }

    protected SecurityLogEntry(Account actingAccount) {
        this.actingAccount = actingAccount;
        this.time = new Date();
    }

    public abstract String getLabel();

    public abstract String getMessage();

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
