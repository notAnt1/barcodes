package edu.grinnell.csc207.exploration;

import java.util.Scanner;

public class StringExploration {

    /**
     * The function intesperse takes an array of Strings and returns a String.
     * 
     * It is supposed to join the Strings stored in the array with commas in between
     * every String.
     * 
     * @param strs
     * @return String result
     */
    public static String intersperse(String[] strs) {
        String result = String.join(",", strs);
        return result;
    }

    /**
     * The function intesperse takes a String and returns a String.
     * 
     * It is supposed to split the Strings within fullName separated with commas,
     * and return it in a form Fisrt Name Middle Name Last Name.
     * 
     * @param fullName
     * @return String
     */
    public static String parseName(String fullName) {
        String[] names = fullName.split(",");
        return names[1] + " " + names[2] + " " + names[0];

    }

    public static String fetchString() {
        Scanner scanner = new Scanner(System.in);
        // the nextLine() method returns the next line from the stream
        // that is used to construct the scanner.
        return scanner.nextLine();
    }

    /**
     * The function forgivingPrompt prompts the user with a yes/no question and 
     * validates their response.
     * 
     * Accepts multiple variations of yes/no answers in a case-insensitive manner:
     * "y", "yes", "yep" returns true
     * "n", "no", "nope" returns false
     * 
     * The prompt repeats until a valid answer is given.
     * 
     * @param question the yes/no question to display to the user
     * @return true if user answers affirmatively, false if user answers negatively
     */
    public static boolean forgivingPrompt(String question) {
        while (true) {
            System.out.println(question);
            String prompt = fetchString();
            prompt = prompt.toLowerCase();
            if (prompt.equals("yes") || prompt.equals("y") || prompt.equals("yep")) {
                return true;
            } else if (prompt.equals("no") || prompt.equals("n") || prompt.equals("nope")) {
                return false;
            }
        }

    }

    // N.B., to run this program, use the following maven command to specify
    // this file as the program entry point rather than the class specified in
    // the pom.xml file:
    //
    // mvn compile exec:java
    // "-Dexec.mainClass=edu.grinnell.csc207.exploration.StringExploration"

    /**
     * Main method for testing and exploring the program's functions.
     * 
     * Tests the following functionality:
     * - intersperse(): Combines an array of strings with a separator
     * - parseName(): Parses a formatted name string back into components
     * - forgivingPrompt(): Prompts the user for input with flexible validation
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        String[] strs = { "Turing", "Alan", "Mathison" };
        String testOne = intersperse(strs);
        System.out.println(testOne);
        System.out.println(parseName(testOne));
        System.out.println(forgivingPrompt("Are you 18+?"));
    }
}
