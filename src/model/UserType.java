package model;

/**
 * Created by Jack on 9/27/16.
 */
public enum UserType {
    USR ("USER", "USR"),
    WKR ("WORKER", "WKR"),
    MGR ("MANAGER", "MGR"),
    ADM ("ADMIN", "ADM");

    private String userType;

    private String code;

    private UserType(String userType, String code) {
        this.userType = userType;
        this.code = code;
    }

    public String getUserType() {
        return userType;
    }

    public String getCode() {
        return code;
    }

    public String toString() {
        return userType;
    }
}
