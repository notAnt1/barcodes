package edu.grinnell.csc207.exploration;
 
import java.util.Scanner;

public class StringExploration {

    /** TODO: fill me in and my docstring! */
    public static String intersperse(String[] strs) {
        String result = String.join(",", strs);
        return result;
    }

    /** TODO: fill me in and my docstring! */
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
    // mvn compile exec:java "-Dexec.mainClass=edu.grinnell.csc207.exploration.StringExploration"
    public static void main(String[] args) {
        // TODO: write your exploration code here!
        String [] strs = { "Turing", "Alan", "Mathison" };
        String testOne = intersperse(strs);
        System.out.println(testOne);
        System.out.println(parseName(testOne));
        System.out.println(forgivingPrompt("Are you 18+?"));
    }
}
