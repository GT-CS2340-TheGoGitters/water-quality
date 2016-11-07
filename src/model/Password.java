package model;

import java.io.Serializable;
import java.security.SecureRandom;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

// Copied from the approved library: https://github.com/defuse/password-hashing
public class Password implements Serializable{
    @SuppressWarnings("serial")
    static public class CannotPerformOperationException extends Exception {
        public CannotPerformOperationException(String message) {
            super(message);
        }

        public CannotPerformOperationException(String message, Throwable source) {
            super(message, source);
        }
    }

    private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

    // These constants may be changed without breaking existing hashes.
    private static final int SALT_BYTE_SIZE = 24;
    private static final int HASH_BYTE_SIZE = 18;
    private static final int PBKDF2_ITERATIONS = 64000;

    // These constants define the encoding and may not be changed.
    protected static final int HASH_SECTIONS = 5;
    protected static final int HASH_ALGORITHM_INDEX = 0;
    protected static final int ITERATION_INDEX = 1;
    protected static final int HASH_SIZE_INDEX = 2;
    protected static final int SALT_INDEX = 3;
    protected static final int PBKDF2_INDEX = 4;

    private byte[] salt;
    private byte[] hash;
    private int iterations;
    private Date created;

    public Password(String password)
            throws CannotPerformOperationException {
        char[] passwordChars = password.toCharArray();

        // Generate a random salt
        SecureRandom random = new SecureRandom();
        salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);

        // Hash the password
        hash = pbkdf2(passwordChars, salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);

        // Store the number of iterations
        iterations = PBKDF2_ITERATIONS;

        // Store when the password was set
        created = new Date();
    }

    public boolean verifyPassword(String password)
            throws CannotPerformOperationException {
        char[] passwordChars = password.toCharArray();

        // Compute the hash of the provided password, using the same salt,
        // iteration count, and hash length
        byte[] testHash = pbkdf2(passwordChars, salt, iterations, hash.length);

        // The password is correct if both hashes match.
        return slowEquals(hash, testHash);
    }

    public Date getCreated() {
        return created;
    }

    /**
     * Compares 2 hashes in constant time to prevent timing attacks.
     *
     * @param a The first hash
     * @param b The second hash
     * @return True if the hashes are equal, false otherwise.
     */
    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++)
            diff |= a[i] ^ b[i];
        return diff == 0;
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes)
            throws CannotPerformOperationException {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException ex) {
            throw new CannotPerformOperationException(
                    "Hash algorithm not supported.",
                    ex
            );
        } catch (InvalidKeySpecException ex) {
            throw new CannotPerformOperationException(
                    "Invalid key spec.",
                    ex
            );
        }
    }
}