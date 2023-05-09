package com.nctu.cryptography.crypto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Substitution {
    public static String plaintext = "abcdefghijklmnopqrstuvwxyz";

    public static String generateRandomCiphertext() {
        char[] characters = plaintext.toCharArray();
        Random rand = new Random();
        for (int i = characters.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            char temp = characters[i];
            characters[i] = characters[j];
            characters[j] = temp;
        }
        return new String(characters);
    }


    public static String encrypt(String text, String key) {
        String result = "";

        for (int i = 0; i < text.length(); ++i) {
            char c = text.charAt(i);
            if (plaintext.indexOf(c) != -1) {
                int in = plaintext.indexOf(c);
                char k = key.charAt(in);

                if (k >= 'A' && k <= 'Z') {
                    k = (char) ((k - 'A') + 'a');
                }
                result += k;
            } else if (plaintext.toUpperCase().indexOf(c) != -1) {
                int in = plaintext.toUpperCase().indexOf(c);
                char k = key.charAt(in);

                if (k >= 'a' && k <= 'z') {
                    k = (char) ((k - 'a') + 'A');
                }
                result += k;
            } else {
                result += text.charAt(i);
            }
        }

        return result;
    }

    public static List<String> getWords(String text) {
        return Stream.of(text.split(" "))
                .map(word -> word.replaceAll("[^A-Za-z]", "").toLowerCase())
                .filter(word -> !word.isEmpty()).collect(Collectors.toList());
    }

    public static String decrypt(String text, String key) {
        String result = "";

        for (int i = 0; i < text.length(); ++i) {
            char c = text.charAt(i);
            if (key.indexOf(c) != -1) {
                int in = key.indexOf(c);
                char k = plaintext.charAt(in);

                if (k >= 'A' && k <= 'Z') {
                    k = (char) ((k - 'A') + 'a');
                }
                result += k;
            } else if (key.toUpperCase().indexOf(c) != -1) {
                int in = key.toUpperCase().indexOf(c);
                char k = plaintext.charAt(in);

                if (k >= 'a' && k <= 'z') {
                    k = (char) ((k - 'a') + 'A');
                }
                result += k;
            } else {
                result += text.charAt(i);
            }
        }

        return result;
    }

    public static Map<String, Integer> countNGramFrequencies(String text, int n) {
        Map<String, Integer> frequencies = new HashMap<>();
        text = text.replaceAll("[^A-Za-z]", "").toUpperCase();
        for (int i = 0; i < text.length(); ++i) {
            String ngram = text.substring(i, i + n);
            int count = frequencies.getOrDefault(ngram, 0) + 1;
            frequencies.put(ngram, count);
            frequencies.put("_total", frequencies.getOrDefault("_total", 0) + count);
        }


        return frequencies;
    }

    public static List<Map.Entry<String, Integer>> sortFrequencies(Map<String, Integer> frequencies) {
        return frequencies.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toList());
    }

}
