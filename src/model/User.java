package model;

/**
 * Created by Jack on 9/20/16.
 */
public class User {
    private String username;
    private String password;
    private String name;
    private UserType account;

    public User() {
        username = "user";
        password = "pass";
        name = "name";
        account = UserType.USR;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() { return name; }

    public UserType getAccount() { return account; }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccount(UserType account) {
        this.account = account;
    }
}
