/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HackingTranspositionCipher;

import TranspositionCipher.TranspositionCipherDecrypt;


/**
 *
 * @author pc
 */
public class HackingTranspositionCipher {
    public static void main(String[] args) {
        String myMessage = "\"iwjv ocTcnuelgkhk mra'.e fp zs\" bosty qrx h buo oeda";

        String hackedMessage = hackTransposition(myMessage);

        if (hackedMessage == null) {
            System.out.println("Failed to hack encryption.");
        } else {
            System.out.println("Copying hacked message to clipboard:");
            System.out.println(hackedMessage);
            // Pyperclip functionality not directly available in Java
            // You may use a library like java.awt.Toolkit to copy to clipboard if needed
        }
    }

    public static String hackTransposition(String message) {
        System.out.println("Hacking...");

        System.out.println("(Press Ctrl-C to quit at any time.)");

        String bestDecryptedText = null;
        double highestEnglishPercentage = 0.0;

        // Brute-force by looping through every possible key
        for (int key = 1; key < message.length(); key++) {
            System.out.println("Trying key #" + key + "...");
            TranspositionCipher.TranspositionCipherDecrypt decrypt = new TranspositionCipherDecrypt();
            String decryptedText = decrypt.decryptMessage(key, message);

            double englishPercentage = DetectEnglish.EnglishChecker.getEnglishCount(decryptedText) * 100;
            if (englishPercentage > highestEnglishPercentage) {
                highestEnglishPercentage = englishPercentage;
                bestDecryptedText = decryptedText;

                // Print the best decryption attempt immediately
                System.out.println();
                System.out.println("Best decryption attempt:");
                System.out.println(bestDecryptedText);
                System.out.println("English percentage: " + highestEnglishPercentage + "%");
                System.out.println();

                // Check if decryption meets English criteria
                if(DetectEnglish.EnglishChecker.isEnglish(decryptedText))
                {
                    System.out.println(decryptedText);
                }
            }
        }
        // If no suitable decryption is found, return the best attempt
        return bestDecryptedText;
    }
}
