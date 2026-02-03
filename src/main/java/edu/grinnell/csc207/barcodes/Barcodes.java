package edu.grinnell.csc207.barcodes;

public class Barcodes {
    /*
     * The array ENCODINGS encodes barcode patterns for digits 0-9. Each row
     * corresponds to a digit, while each sub-array contains the widths of modules
     * that form the barcode pattern for that digit.
     */
    private static int[][] ENCODINGS = {
            { 3, 2, 1, 1 }, { 2, 2, 2, 1 }, { 2, 1, 2, 2 }, { 1, 4, 1, 1 },
            { 1, 1, 3, 2 }, { 1, 2, 3, 1 }, { 1, 1, 1, 4 }, { 1, 3, 1, 2 },
            { 1, 2, 1, 3 }, { 3, 1, 1, 2 }
    };

    private static String[] HELPERS = { "111111111", "010", "10101" };

    /**
     * The function isValidCode takes a String code as input and returns a boolean.
     * 
     * It checks whether the code provided by user is a valid code in 2 possible
     * ways.
     * First, it checks whether the length of the code is 12 digits.
     * Seccond, if passed test one, it checks whether each character in the provided code
     * is an integer between 0 and 9.
     */
    public static boolean isValidCode(String code) {
        if (!(code.length() == 12)) {
            return false;
        }

        for (int i = 0; i < code.length(); i++) {
            if (!(Character.isDigit(code.charAt(i)))) {
                return false;
            }

        }
        return true;
    }

    public static int toDigit(char ch) {
        return ch - '0';
    }

    /**
     * Computes a check digit from a code. C is a weighed SUM of the first 11 digits of a barcode % 10, then if
     * it's 0, return 0, if not, return 10 -  C. 
     * 
     * @return int the check digit. 
     * @params code is the string code that the user provides.The function computeCheckDigit takes a String code as input and returns an integer.
     * 
     * It compute the check digit for the given number code using the UPC-A formula and first
     * eleven numbers of the code. The function returns the integer computed, which helps to
     * identify whether the given code is a valid code as its 12th character supposed to euate to the
     * computed and returned integer. 
     */
    public static int computeCheckDigit(String code) {
        if (!(isValidCode(code))) {
            return -1;
        }
        // according to Google.com, UPC-A check digits are created with a weighted SUM, not
        // a weighted product!!!
        int check = 0;
        int digit;
        for (int i = 0; i < code.length() - 1; i++) {
            digit = toDigit(code.charAt(i));
            if ((i + 1) % 2 == 0) {
                check += digit;
            } else {
                check += 3 * digit;
            }
        }
        digit = check % 10;

        if (digit == 0) {
            return 0;
        } else {
            return 10 - digit;
        }
    }

    // very trivial, prints squares, 0 for black, 1 for white.
    public static void printSquare(int color) {
        if (color == 1) {
            System.out.print("\033[37m█\033[0m");
        } else {
            System.out.print("\033[30m█\033[0m");
        }

    }

    /**
     * Prints a single row of the barcode for the given code.
     *
     * @param code the 12-digit UPC-A code to print
     */
    public static void printBarcodeRow(String code) {
        int colorDigit = 1; // sets color Digit to 1 (white), since printing pattern is always white first.
        int digit;

        for (int k = 0; k < 2; k++) { // iterates over first 2 elements of helper patterns (9 * W and WBW)

            for (int j = 0; j < HELPERS[k].length(); j++) { // iterates over each digit in the helper pattern
                printSquare(toDigit(HELPERS[k].charAt(j)));
            }
        }

        for (int l = 0; l < 6; l++) { // first 6 digit of the code
            digit = toDigit(code.charAt(l));
            for (int m = 0; m < 4; m++) { // every digit "pattern" has 4 components
                for (int o = 0; o < ENCODINGS[digit][m]; o++) {
                    printSquare(colorDigit % 2);
                }
                colorDigit++; //colorDigit becomes either even/odd, with the pattern matching b/w
            }

        }

        for (int p = 0; p < HELPERS[2].length(); p++) { // middle helper bars
            printSquare(toDigit(HELPERS[2].charAt(p)));
        }

        colorDigit = 0;
        for (int q = 6; q < 12; q++) { //second half, everything is inverted so the pattern reverses, but same logic.
            digit = toDigit(code.charAt(q));
            for (int t = 0; t < 4; t++) {
                for (int u = 0; u < ENCODINGS[digit][t]; u++) {
                    printSquare(colorDigit % 2);
                }
                colorDigit++;
            }

        }

        for (int r = 1; r > -1; r--) { // reprinting helper pattetns 1 and 0
            for (int s = 0; s < HELPERS[r].length(); s++) {
                printSquare(toDigit(HELPERS[r].charAt(s)));
            }
        }
        System.out.println(""); //newline to get to new row.
    }

    /**
     * Main function—handles user input and returns error messages (no exceptions), and prints a UPC-A Barcode to the terminal
     * dependent on user specifications (the code and the height/number of rows).
     *
     *
     * @param args is the string of digits to print and the number of rows.
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: barcode <upc-a code> <height>");
            return;
        }
        if (!isValidCode(args[0])) {
            System.out.println("Code must be a string of 12 digits.");
            return;
        }
        if (Integer.parseInt(args[1]) < 1) {
            System.out.println("Height must be a positive integer.");
            return;
        }
        if (toDigit(args[0].charAt(11)) != computeCheckDigit(args[0])) {
            System.out.println("Expected check digit " + computeCheckDigit(args[0]) + " but found "
                    + toDigit(args[0].charAt(11)) + ".");
            return;
        }
        
        int rows = Integer.parseInt(args[1]);

        for (int i = 0; i < rows; i++) { // iterated over number of rows
            printBarcodeRow(args[0]);
        }
    }
}
