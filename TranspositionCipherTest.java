/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TranspositionCipher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
/**
 *
 * @author pc
 */
public class TranspositionCipherTest {
    public static void main(String[] args) {
        Random random = new Random(42); // Set the random seed to a static value

        for (int i = 0; i < 20; i++) { // Run 20 tests
            // Generate random messages to test.
            // The message will have a random length:
            String baseMessage = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            int repeat = random.nextInt(37) + 4;
            StringBuilder messageBuilder = new StringBuilder();
            for (int j = 0; j < repeat; j++) {
                messageBuilder.append(baseMessage);
            }
            String message = messageBuilder.toString();

            // Convert the message string to a list to shuffle it.
            List<Character> messageList = new ArrayList<>();
            for (char c : message.toCharArray()) {
                messageList.add(c);
            }

            Collections.shuffle(messageList, random);
            StringBuilder shuffledMessage = new StringBuilder();
            for (char c : messageList) {
                shuffledMessage.append(c);
            }

            message = shuffledMessage.toString();

            System.out.println("Test #" + (i + 1) + ": \"" + message.substring(0, 50) + "...\"");

            // Check all possible keys for each message.
            for (int key = 1; key < message.length(); key++) {
                TranspositionCipherEncypt encrypt = new TranspositionCipherEncypt();
                String encrypted  =  encrypt.encryptMessage(key, message);
                TranspositionCipherDecrypt decrypt = new TranspositionCipherDecrypt();
                String decrypted = decrypt.decryptMessage(key, encrypted);

                // If the decryption doesn't match the original message, display an error message and quit.
                if (!message.equals(decrypted)) {
                    System.out.println("Mismatch with key " + key + " and message " + message);
                    System.out.println(decrypted);
                    System.exit(1);
                }
            }
        }
        System.out.println("Transposition cipher test passed.");
    }
}
