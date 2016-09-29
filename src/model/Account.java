package model;

/**
 * Created by Jack on 9/20/16.
 */
public class Account {
    private String username;
    private String password;
    private String name;
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

    public void setAccountType(AccountType type) {
        this.type = type;
    }
}
