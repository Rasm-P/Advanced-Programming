package mayer;

import java.util.Scanner;

/**
 *
 * @author Rasmus2
 */
public class TextUI {

    private static Scanner scanner = new Scanner(System.in);

    public static void print(String s) {
        System.out.print(s);
    }

    public static void println(String s) {
        System.out.println(s);
    }

    public static String getString() {
        return scanner.nextLine();
    }

    public static int getInteger() {
        while (true) {
            String sNum = getString();
            try {
                return Integer.parseInt(sNum);
            } catch (NumberFormatException e) {
                print("Please enter an integer! \n: ");
            }
        }
    }

    static int choice(String[] choices) {
        if (choices == null || choices.length == 0) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < choices.length; ++i) {
            println("press " + i + " to " + choices[i]);
        }
        println("\nPlease choose: ");
        return getInteger();
    }
}
