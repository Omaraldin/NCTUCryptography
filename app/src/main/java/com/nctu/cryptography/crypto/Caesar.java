package com.nctu.cryptography.crypto;

import java.util.HashMap;
import java.util.Map;

public class Caesar {
    public static String identifiers = "abcdefghijklmnopqrstuvwxyz";

    public static String identifiersUpper = identifiers.toUpperCase();

    public static String encrypt(String text, int key) {
        String cipher = "";

        for (int i = 0; i < text.length(); ++i) {
            int index = identifiers.indexOf(text.charAt(i));
            boolean isUpper = identifiersUpper.indexOf(text.charAt(i)) != -1;

            if (isUpper) {
                index = identifiersUpper.indexOf(text.charAt(i));
            }

            if (index != -1) {
                index = Math.floorMod(index + key, identifiers.length());
                if (isUpper) {
                    cipher += identifiersUpper.charAt(index);
                } else {
                    cipher += identifiers.charAt(index);
                }
            } else {
                cipher += text.charAt(i);
            }
        }

        return cipher;
    }

    public static String decrypt(String cipher, int key) {
        String text = "";
        for (int i = 0; i < cipher.length(); ++i) {
            int index = identifiers.indexOf(cipher.charAt(i));
            boolean isUpper = identifiersUpper.indexOf(cipher.charAt(i)) != -1;

            if (isUpper) {
                index = identifiersUpper.indexOf(cipher.charAt(i));
            }

            if (index != -1) {
                index = Math.floorMod(index - key, identifiers.length());
                if (isUpper) {
                    text += identifiersUpper.charAt(index);
                } else {
                    text += identifiers.charAt(index);
                }
            } else {
                text += cipher.charAt(i);
            }
        }
        return text;
    }

    public static Map<Integer, String> bruteforce(String text) {
        Map<Integer, String> result = new HashMap<>();

        for (int key = 1; key <= 25; ++key) {
            result.put(key, decrypt(text, key));
        }

        return result;
    }
}
