package model;

import java.io.Serializable;
import java.util.Date;

public class Account implements Serializable{
    private final String username;
    private Password password;
    private String name;
    private String emailAddress = null;
    private String title = null;
    private String homeAddress = null;
    public boolean isBanned = false;

    private int incorrectAttempts = 0;

    private AccountType type;

    public Account(String name, String username, String password, AccountType type) throws Password.CannotPerformOperationException {
        this.username = username;
        this.name = name;
        this.password = new Password(password);
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public boolean verifyPassword(String password) throws Password.CannotPerformOperationException {
        return this.password.verifyPassword(password);
    }

    public Date getLastPasswordChange(){
        return password.getCreated();
    }

    public String getName() { return name; }

    public AccountType getAccountType() { return type; }

    public void setPassword(String password) throws Password.CannotPerformOperationException {
        this.password = new Password(password);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    /**
     * Increments the number of incorrect login attempts
     */
    public void incrementIncorrectAttempts() {
        this.incorrectAttempts++;
    }

    /**
     * Resets the number of incorrect login attempts after a successful login
     */
    public void resetIncorrectAttempts() {
        this.incorrectAttempts = 0;
    }

    /**
     * Checks to see whether someone has tried to log in incorrectly more than 3 times
     * @return whether or not the account should be locked
     */
    public boolean getIsLocked() {
        return this.incorrectAttempts >= 3;
    }

    public boolean getIsBanned() {
        return isBanned;
    }

    public void ban() {
        isBanned = true;
    }

    public void unban() {
        isBanned = false;
    }

    @Override
    public String toString() {
        return username;
    }
}
