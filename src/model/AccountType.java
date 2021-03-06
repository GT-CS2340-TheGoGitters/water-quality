package model;

public enum AccountType {
    USR ("USER", "USR"),
    WKR ("WORKER", "WKR"),
    MGR ("MANAGER", "MGR"),
    ADM ("ADMIN", "ADM");

    private final String userType;

    private final String code;

    /**
     * Constructor for AccountType enum
     * @param userType type of account
     * @param code account type code
     */
    AccountType(String userType, String code) {
        this.userType = userType;
        this.code = code;
    }

    public String getUserType() {
        return userType;
    }

    public String toString() {
        return userType;
    }
}
