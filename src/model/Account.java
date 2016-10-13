package model;

/**
 * Created by Jack on 9/20/16.
 */
public class Account {
    private String username;
    private String password;
    private String name;
    private String emailAddress = null;
    private String title = null;
    private String homeAddress = null;

    private AccountType type;

    public Account(String name, String username, String password, AccountType type) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() { return name; }

    public AccountType getAccountType() { return type; }

    public void setPassword(String password) {
        this.password = password;
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

    public void setAccountType(AccountType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return username;
    }
}
