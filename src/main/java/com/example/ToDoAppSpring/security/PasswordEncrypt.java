package com.example.ToDoAppSpring.security;

import java.util.Base64;

public class PasswordEncrypt {

    private static final int SHIFT = 3;

    public static String encrypt(String data) {
        StringBuilder encrypted = new StringBuilder();
        for (char character : data.toCharArray()) {
            encrypted.append((char) (character + SHIFT));
        }
        return Base64.getEncoder().encodeToString(encrypted.toString().getBytes());
    }

    public static String decrypt(String encryptedData) {
        String decrypted = new String(Base64.getDecoder().decode(encryptedData));
        StringBuilder original = new StringBuilder();
        for (char character : decrypted.toCharArray()) {
            original.append((char) (character - SHIFT));
        }
        return original.toString();
    }
}
