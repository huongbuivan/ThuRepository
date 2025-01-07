package com.example.java_project.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESUtil {
    private static final String AES = "AES";
    private static byte[] SECRET_KEY;

    // Private constructor to prevent instantiation
    private AESUtil() {
        // This constructor is intentionally left empty
    }

    // Set the SECRET_KEY via the setter method
    public static void setSecretKey(String base64SecretKey) {
        // Decode the Base64 string to get the raw key bytes
        byte[] decodedKey = Base64.getDecoder().decode(base64SecretKey);

        // Ensure the decoded key is of valid length (16, 24, or 32 bytes)
        if (decodedKey.length != 16 && decodedKey.length != 24 && decodedKey.length != 32) {
            throw new IllegalArgumentException("AES key length must be 16, 24, or 32 bytes.");
        }

        SECRET_KEY = decodedKey;
    }

    // Encrypt a string
    public static String encrypt(String plainText) throws Exception {
        if (SECRET_KEY == null) {
            throw new IllegalStateException("SECRET_KEY is not set.");
        }
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY, AES);
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Decrypt a string
    public static String decrypt(String encryptedText) throws Exception {
        if (SECRET_KEY == null) {
            throw new IllegalStateException("SECRET_KEY is not set.");
        }
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY, AES);
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decryptedBytes);
    }
}
