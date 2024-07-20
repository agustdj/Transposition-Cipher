/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DetectEnglish;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author pc
 */
public class EnglishChecker {

    private static final String UPPERLETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LETTERS_AND_SPACE = UPPERLETTERS + UPPERLETTERS.toLowerCase() + " \t\n";
    private static final Set<String> ENGLISH_WORDS = loadDictionary();

    private static Set<String> loadDictionary() {
        Set<String> englishWords = new HashSet<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get("dictionary.txt"));
            for (String word : lines) {
                englishWords.add(word.trim().toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return englishWords;
    }

    public static double getEnglishCount(String message) {
        message = message.toUpperCase();
        message = removeNonLetters(message);
        String[] possibleWords = message.split("\\s+");
        if (possibleWords.length == 0) {
            return 0.0; // no words at all, so return 0.0
        }

        int matches = 0;
        for (String word : possibleWords) {
            if (ENGLISH_WORDS.contains(word.toLowerCase())) {
                matches += 1;
            }
        }
        return (double) matches / possibleWords.length;
    }

    private static String removeNonLetters(String message) {
        StringBuilder lettersOnly = new StringBuilder();
        for (char symbol : message.toCharArray()) {
            if (LETTERS_AND_SPACE.indexOf(symbol) != -1) {
                lettersOnly.append(symbol);
            }
        }
        return lettersOnly.toString();
    }

    public static boolean isEnglish(String message, int wordPercentage, int letterPercentage) {
        // By default, 20% of the words must exist in the dictionary file, and
        // 85% of all the characters in the message must be letters or spaces
        // (not punctuation or numbers).
        boolean wordsMatch = getEnglishCount(message) * 100 >= wordPercentage;
        int numLetters = removeNonLetters(message).length();
        double messageLettersPercentage = (double) numLetters / message.length() * 100;
        boolean lettersMatch = messageLettersPercentage >= letterPercentage;
        return wordsMatch && lettersMatch;
    }

    public static boolean isEnglish(String message) {
        return isEnglish(message, 20, 85);
    }

    public static void main(String[] args) {
        String testMessage1 = "hello world";
        String testMessage2 = "Th1s 1s a t3st.";

        System.out.println("Message 1 is English? " + isEnglish(testMessage1, 20, 85));
        System.out.println("Message 2 is English? " + isEnglish(testMessage2, 20, 85));
    }
}
