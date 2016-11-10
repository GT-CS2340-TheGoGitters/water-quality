package model;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;

public final class PasswordResetCode {
    private final static long MILLIS_PER_HOUR = 60 * 60 * 1000L;


    private static final SecureRandom random = new SecureRandom();

    private final String code;
    private final Date created;

    public PasswordResetCode() {
        code = new BigInteger(130, random).toString(32);
        created = new Date();
    }

    public boolean validate(String enteredCode) {
        boolean moreThanHour = Math.abs(created.getTime() - new Date().getTime()) > MILLIS_PER_HOUR;

        return code.equals(enteredCode) && !moreThanHour;
    }

    public String getCode(){
        return this.code;
    }
}
