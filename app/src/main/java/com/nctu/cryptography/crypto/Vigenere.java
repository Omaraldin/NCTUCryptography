package com.nctu.cryptography.crypto;

import java.util.Locale;

public class Vigenere {
    public static String identifiers = "abcdefghijklmnopqrstuvwxyz";

    public static String identifiersUpper = identifiers.toUpperCase();

    private static String generateKey(String text, String key) {
        String new_key = "", temp_key = "";
        int x = 0;

        // Filter
        for (int i = 0; i < key.length(); ++i) {
            if (identifiers.indexOf(key.charAt(i)) != -1 || identifiersUpper.indexOf(key.charAt(i)) != -1) {
                temp_key += key.charAt(i);
            }
        }

        if (temp_key.isEmpty()) return "";
        // Fill
        for (int i = 0; i < text.length(); ++i) {
            if (identifiers.indexOf(text.charAt(i)) != -1 || identifiersUpper.indexOf(text.charAt(i)) != -1) {
                new_key += temp_key.charAt(x++ % temp_key.length());
            }
        }

        return new_key.toLowerCase();
    }


    public static String encrypt(String text, String key) {
        key = generateKey(text, key);

        String cipher = "";
        int x = 0;

        for (int i = 0; i < text.length(); ++i) {
            int index = identifiers.indexOf(text.charAt(i));
            boolean isUpper = identifiersUpper.indexOf(text.charAt(i)) != -1;

            if (isUpper) {
                index = identifiersUpper.indexOf(text.charAt(i));
            }

            if (index != -1) {
                int keyIndex = identifiers.indexOf(key.charAt(x++));
                index = Math.floorMod(index + keyIndex, identifiers.length());
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


    public static String decrypt(String cipher, String key) {
        key = generateKey(cipher, key);
        if (key.isEmpty()) return "";

        String text = "";
        int x = 0;

        for (int i = 0; i < cipher.length(); ++i) {
            int index = identifiers.indexOf(cipher.charAt(i));
            boolean isUpper = identifiersUpper.indexOf(cipher.charAt(i)) != -1;

            if (isUpper) {
                index = identifiersUpper.indexOf(cipher.charAt(i));
            }

            if (index != -1) {
                int keyIndex = identifiers.indexOf(key.charAt(x++));
                index = Math.floorMod(index - keyIndex, identifiers.length());

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
}
