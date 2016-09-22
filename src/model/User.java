package model;

/**
 * Created by Jack on 9/20/16.
 */
public class User {
    private String username;
    private String password;

    public User() {
        username = "user";
        password = "pass";
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
