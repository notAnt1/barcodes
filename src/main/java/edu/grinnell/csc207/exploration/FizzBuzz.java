package edu.grinnell.csc207.exploration;

public class FizzBuzz {
    // N.B., to run this program, use the following maven command to specify
    // this file as the program entry point rather than the class specified in
    // the pom.xml file:
    //
    // mvn compile exec:java -q "-Dexec.mainClass=edu.grinnell.csc207.exploration.FizzBuzz"
    public static void main(String[] args) {
        for (int i = 1; i <= Integer.parseInt(args[0]); i++){
            if(((i % 3) == 0) && ((i % 5) == 0)) {
                System.out.println("fizzbuzz");
            }
            else if((i % 3) == 0){
                System.out.println("fizz");
            }
            else if((i % 5) == 0){
                System.out.println("buzz");
            }
            else
            {
                System.out.println(i);  
            }
            
        }
    }
}
