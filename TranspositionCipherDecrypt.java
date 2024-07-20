
package TranspositionCipher;

public class TranspositionCipherDecrypt {
    public static String decryptMessage(int key, String message) {
        // The transposition decrypt function will simulate the "columns" and
        // "rows" of the grid that the plaintext is written on by using a list
        // of strings. First, we need to calculate a few values.

        // The number of "columns" in our transposition grid:
        int numOfColumns = (int) Math.ceil((double) message.length() / key);
        // The number of "rows" in our grid will need:
        int numOfRows = key;
        // The number of "shaded boxes" in the last "column" of the grid:
        int numOfShadedBoxes = (numOfColumns * numOfRows) - message.length();

        // Each string in plaintext represents a column in the grid.
        String[] plaintext = new String[numOfColumns];
        for (int i = 0; i < numOfColumns; i++) {
            plaintext[i] = "";
        }

        // The col and row variables point to where in the grid the next
        // character in the encrypted message will go.
        int col = 0;
        int row = 0;

        for (char symbol : message.toCharArray()) {
            plaintext[col] += symbol;
            col += 1; // point to next column

            // If there are no more columns OR we're at a shaded box, go back to
            // the first column and the next row.
            if (col == numOfColumns || (col == numOfColumns - 1 && row >= numOfRows - numOfShadedBoxes)) {
                col = 0;
                row += 1;
            }
        }

        return String.join("", plaintext);
    }
}
