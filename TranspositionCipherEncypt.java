
package TranspositionCipher;


public class TranspositionCipherEncypt {
    public static String encryptMessage(int key, String message) {
        // Initialize a list of strings to hold each column of the ciphertext
        StringBuilder[] ciphertext = new StringBuilder[key];
        for (int i = 0; i < key; i++) {
            ciphertext[i] = new StringBuilder();
        }

        // Loop through each column in the ciphertext
        for (int col = 0; col < key; col++) {
            int pointer = col;

            // Keep looping until pointer goes past the length of the message
            while (pointer < message.length()) {
                // Place the character at pointer in message at the end of the current column
                ciphertext[col].append(message.charAt(pointer));

                // Move pointer over by key to get to the next character in the column
                pointer += key;
            }
        }

        // Convert the StringBuilder array into a single string and return it
        StringBuilder result = new StringBuilder();
        for (StringBuilder sb : ciphertext) {
            result.append(sb.toString());
        }
        return result.toString();
    }
}
