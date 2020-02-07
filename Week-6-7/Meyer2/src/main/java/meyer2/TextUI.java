package meyer2;

import java.util.Scanner;

/**
 *
 * @author Rasmus2
 */
public class TextUI {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void print(String s) {
        System.out.print(s);
    }

    public static void println(String s) {
        System.out.println(s);
    }

    public static String getString() {
        return SCANNER.nextLine();
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

    public static int choice(String[] choices) {
        if (choices == null || choices.length == 0) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < choices.length; ++i) {
            println("press " + i + " to " + choices[i]);
        }
        println("\nPlease choose: ");
        return getInteger();
    }

    public static void gameWinner(PlayerCtrlImpl winner) {
        println("Game over!");
        println("The winner is " + winner.getName() + " with " + winner.getHealth() + " health!");
    }

    static void whatAiName(int i) {
        println("What's the name of AI " + i + "?");
    }

    static void howManyAi() {
        println("How many AI's?");
    }

    static void whatPlayerName(int i) {
        println("What's your name, player " + i + "?");
    }

    static void howManyPlayers() {
        println("How many players?");
    }

    static void welcome() {
        println("Welcome to the game");
    }

    static void playerIsOut(PlayerCtrlImpl player) {
        println("Player " + player.getName() + " hit 0 health, and is out!");
    }

    static void playerSaysTheyRolled(PlayerCtrlImpl player) {
        println(player.getName() + " says they rolled " + player.getShowToOtherPlayer());
    }

    static void itIsYouTurn(String name, int health) {
        println("It's your turn " + name + ", your health is " + health + "! Press Enter to roll!");
        TextUI.getString();
    }

    static void youRolled(String name, int roll, int d1, int d2) {
        println(name + " you rolled " + roll);
        if (roll == 21) {
            TextUI.println("That is Meyer!");
        } else if (roll == 31) {
            TextUI.println("That is Lille-meyer!");
        } else if (d1 == d2) {
            TextUI.println("That is a pair of: " + d1 + d2);
        }
    }

    static void answerTrue(int roll) {
        println("You awnser with the true roll of: " + roll);
    }

    static int answerBluff() {
        TextUI.println("Make your buff!");
        int bluff = TextUI.getInteger();
        TextUI.println("You awnser with a bluff of: " + bluff);
        return bluff;
    }

    static void doYouBelieve(String name, String playerName) {
        println(name + " do you believe that " + playerName + " rolled this?");
    }

    static void pressEnterToRoll(String name) {
        println(name + " press Enter to roll!");
        getString();
    }

    static void turnChoiceRoll(int turnRoll) {
        println("You rolled " + turnRoll);
    }

    static void rolledTheSame() {
        println("You both rolled the same!");
    }

    static void rolledMeyer(String name, String playerName) {
        println("Meyer! " + name + " rolled higher than " + playerName + ", who lose -1 health");
    }

    static void rolledLilleMeyer(String name, String playerName) {
        println("Lille-meyer! " + name + " rolled higher than " + playerName + ", who lose -1 health");
    }

    static void rolledPair1(String name, String playerName) {
        println("A pair!, " + name + " rolled higher than " + playerName + ", who lose -1 health");
    }

    static void rolledHigher(String name, String playerName) {
        println("" + name + " rolled higher than " + playerName + ", who lose -1 health");
    }

    static void rolledLower(String name, String playerName, int show) {
        println("" + name + " rolled lower than " + playerName + " who rolled " + show + ", and " + name + " lose -1 health");
    }

    static void wasIncorrect(String name) {
        println(name + " was incorrect. It was true and " + name + " lose -1 health");
    }

    static void wasCorrect(String name, String playerName) {
        println(name + " was correct. It was a bluff and " + playerName + " loses -1 health");
    }

}
