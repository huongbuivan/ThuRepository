package com.example.java_project.security;

import com.example.java_project.annotations.Encrypted;
import com.example.java_project.utils.AESUtil;

import java.lang.reflect.Field;

public class EncryptionProcessor {
    // Private constructor to prevent instantiation
    private EncryptionProcessor() {
        // This constructor is intentionally left empty
    }

    public static void encryptFields(Object entity) {
        processFields(entity, true);
    }

    public static void decryptFields(Object entity) {
        processFields(entity, false);
    }

    private static void processFields(Object entity, boolean encrypt) {
        AESUtil.setSecretKey("u0TQdGKlyza2DYud3DpaPQ==");
        Field[] fields = entity.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Encrypted.class)) {
                field.setAccessible(true); // Allow access to private fields
                try {
                    Object value = field.get(entity);
                    if (value instanceof String strValue) {
                        String processedValue = encrypt
                                ? AESUtil.encrypt(strValue)
                                : AESUtil.decrypt(strValue);
                        field.set(entity, processedValue);
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Error processing encrypted field", e);
                }
            }
        }
    }
}
