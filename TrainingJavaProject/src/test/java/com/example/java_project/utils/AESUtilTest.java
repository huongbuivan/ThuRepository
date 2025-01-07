package com.example.java_project.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AESUtilTest {
    private static final String VALID_KEY = "u0TQdGKlyza2DYud3DpaPQ=="; // Base64 encoded key (16 bytes)
    private static final String INVALID_KEY = "c3VwZXJzZWNyZXRrZXk="; // Invalid key to trigger exception
    private static final String PLAIN_TEXT = "Hello, World!";
    private static final String ENCRYPTED_TEXT = "oz8/AwDtcWn3dUL5EupsWw=="; // Pre-generated valid encrypted text

    @BeforeEach
    public void setUp() {
        // Set up a valid SECRET_KEY before each test
        AESUtil.setSecretKey(VALID_KEY);
    }

    @Test
    void testSetSecretKey_validKey() {
        // Set a valid key and ensure no exception is thrown
        assertDoesNotThrow(() -> AESUtil.setSecretKey(VALID_KEY));
    }

    @Test
    void testSetSecretKey_invalidKey() {
        // Try setting an invalid key and expect an IllegalArgumentException
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            AESUtil.setSecretKey(INVALID_KEY);
        });
        assertTrue(exception.getMessage().contains("AES key length must be 16, 24, or 32 bytes."));
    }

    @Test
    void testEncrypt_validPlainText() throws Exception {
        // Encrypt the plain text and check if the result is not null or empty
        String encryptedText = AESUtil.encrypt(PLAIN_TEXT);
        assertNotNull(encryptedText);
        assertFalse(encryptedText.isEmpty());
        assertNotEquals(PLAIN_TEXT, encryptedText); // Ensure the encrypted text is different from the plain text
    }

    @Test
    void testDecrypt_validEncryptedText() throws Exception {
        // Decrypt a previously encrypted text and verify it matches the original plain text
        String decryptedText = AESUtil.decrypt(ENCRYPTED_TEXT);
        assertEquals(PLAIN_TEXT, decryptedText);
    }

    @Test
    void testEncrypt_decrypt_roundTrip() throws Exception {
        // Test round-trip encryption and decryption
        String encryptedText = AESUtil.encrypt(PLAIN_TEXT);
        String decryptedText = AESUtil.decrypt(encryptedText);
        assertEquals(PLAIN_TEXT, decryptedText); // Ensure decrypted text is same as the original
    }
}
