package org.example;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static AtomicInteger threeCounter = new AtomicInteger();
    public static AtomicInteger fourCounter = new AtomicInteger();
    public static AtomicInteger fiveCounter = new AtomicInteger();

    public static void main(String[] args) {

        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        new Thread(() -> polVerifier(texts)).start();
        new Thread(() -> abVerifier(texts)).start();
        new Thread(() -> repVerifier(texts)).start();

        System.out.println("Красивых слов с длиной 3 " + threeCounter);
        System.out.println("Красивых слов с длиной 4 " + fourCounter);
        System.out.println("Красивых слов с длиной 5 " + fiveCounter);

    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    static boolean isPalindrome(String string) {
        int length = string.length();
        for (int i = 0; i < (length / 2); i++) {
            if (string.charAt(i) != string.charAt(length - i - 1)) {
                return false;
            }
        }
        return true;
    }

    static boolean isRepeat(String string) {
        boolean flag = true;
        for (int i = 1; i < string.length(); i++) {
            if (string.charAt(i) != string.charAt(i - 1)) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    static boolean isAlphabetical(String string) {
        char[] sorted = string.toCharArray();
        Arrays.sort(sorted);
        StringBuilder builder = new StringBuilder();

        for (char c : sorted) {
            builder.append(c);
        }
        String sortedString = builder.toString();
        return sortedString.equals(string);
    }

    static void abVerifier(String[] strings) {
        for (String string : strings) {
            if (isAlphabetical(string)){
                if (string.length() == 3) {
                    threeCounter.getAndIncrement();
                }
            if (string.length() == 4) {
                fourCounter.getAndIncrement();
            }
            if (string.length() == 5) {
                fiveCounter.getAndIncrement();
            }}
        }
    }
    static void repVerifier(String[] strings) {
        for (String string : strings) {
            if (isRepeat(string)){
                if (string.length() == 3) {
                    threeCounter.getAndIncrement();
                }
                if (string.length() == 4) {
                    fourCounter.getAndIncrement();
                }
                if (string.length() == 5) {
                    fiveCounter.getAndIncrement();
                }}
        }
    }
    static void polVerifier(String[] strings) {
        for (String string : strings) {
            if (isPalindrome(string)){
                if (string.length() == 3) {
                    threeCounter.getAndIncrement();
                }
                if (string.length() == 4) {
                    fourCounter.getAndIncrement();
                }
                if (string.length() == 5) {
                    fiveCounter.getAndIncrement();
                }}
        }
    }
}