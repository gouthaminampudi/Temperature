package com.arnav.advent;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberExtractor {
    private static final Map<String, Integer> numberWords = new HashMap<>();

    static {
        numberWords.put("zero", 0);
        numberWords.put("one", 1);
        numberWords.put("two", 2);
        numberWords.put("three", 3);
        numberWords.put("four", 4);
        numberWords.put("five", 5);
        numberWords.put("six", 6);
        numberWords.put("seven", 7);
        numberWords.put("eight", 8);
        numberWords.put("nine", 9);
        // Add more number words if necessary
    }

    public static void main(String[] args) {
        String input = "sixrestsix8wasten8fivefive";
        String numericString = replaceWordsWithNumbers(input);
        System.out.println("Extracted Numbers: " + numericString);
    }

    private static String replaceWordsWithNumbers(String input) {
        for (Map.Entry<String, Integer> entry : numberWords.entrySet()) {
            input = input.replaceAll(entry.getKey(), entry.getValue().toString());
        }
        return extractNumerals(input);
    }

    private static String extractNumerals(String input) {
        StringBuilder numerals = new StringBuilder();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            numerals.append(matcher.group());
        }

        return numerals.toString();
    }
}

