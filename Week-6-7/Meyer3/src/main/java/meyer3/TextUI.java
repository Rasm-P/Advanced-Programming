package meyer3;

import java.io.IOException;
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

    public static int getIntegerOnline(EchoClientHandler eco) throws IOException {
        eco.sendMessage("...");
        while (true) {
            String sNum = eco.readMessage();
            try {
                return Integer.parseInt(sNum);
            } catch (NumberFormatException e) {
                eco.sendMessage("Please enter an integer! \n: ");
                eco.sendMessage("...");
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
        println("Please choose: ");
        return getInteger();
    }

    public static int choiceOnline(String[] choices, EchoClientHandler eco) throws IOException {
        if (choices == null || choices.length == 0) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < choices.length; ++i) {
            eco.sendMessage("press " + i + " to " + choices[i]);
        }
        eco.sendMessage("Please choose: ");
        return getIntegerOnline(eco);
    }

    public static void gameWinner(String name, int health) {
        println("Game over!");
        println("The winner is " + name + " with " + health + " health!");
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

    static void playerIsOut(String name) {
        println("Player " + name + " hit 0 health, and is out!");
    }

    static void playerSaysTheyRolled(String playerName, int show) {
        println(playerName + " says they rolled " + show);
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

    static int answerBluffOnline(EchoClientHandler eco) throws IOException {
        eco.sendMessage("Make your buff!");
        int bluff = TextUI.getIntegerOnline(eco);
        eco.sendMessage("You awnser with a bluff of: " + bluff);
        return bluff;
    }

    static void doYouBelieve(String name, String playerName) {
        println(name + " do you believe that " + playerName + " rolled this?");
    }

    static void pressEnterToRoll(String name) {
        println(name + " press Enter to roll!");
        getString();
    }

    static void turnChoiceRoll(String name, int turnRoll) {
        println(name + " rolled " + turnRoll);
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
        println(name + " rolled higher than " + playerName + ", who lose -1 health");
    }

    static void rolledLower(String name, String playerName, int show) {
        println(name + " rolled lower than " + playerName + " who rolled " + show + ", and " + name + " lose -1 health");
    }

    static void wasIncorrect(String name) {
        println(name + " was incorrect. It was true and " + name + " lose -1 health");
    }

    static void wasCorrect(String name, String playerName) {
        println(name + " was correct. It was a bluff and " + playerName + " loses -1 health");
    }

    static void itIsPlayerTurn(String name, int health) {
        println("It's " + name + "'s turn, their health is " + health + "!");
    }

    static void yesIDoBelieve(String name) {
        println(name + ": Yes i do!");
    }

    static void noIDoBelieve(String name) {
        println(name + ": No i dont, it is a bluff!");
    }

    static void enterIp() {
        println("Please enter host IP:");
    }

    static void whatIsClientName() {
        println("What's your name?");
    }

    static void itIsYouTurnOnline(String name, int health, EchoClientHandler echoClientHandler) throws IOException {
        echoClientHandler.sendMessage("It's your turn " + name + ", your health is " + health + "! Press Enter to roll!");
        echoClientHandler.sendMessage("...");
        echoClientHandler.readMessage();
    }

    static void youRolledOnline(String name, int roll, int d1, int d2, EchoClientHandler echoClientHandler) {
        echoClientHandler.sendMessage(name + " you rolled " + roll);
        if (roll == 21) {
            echoClientHandler.sendMessage("That is Meyer!");
        } else if (roll == 31) {
            echoClientHandler.sendMessage("That is Lille-meyer!");
        } else if (d1 == d2) {
            echoClientHandler.sendMessage("That is a pair of: " + d1 + d2);
        }
    }

    static void answerTrueOnline(int showToOtherPlayer, EchoClientHandler echoClientHandler) {
        echoClientHandler.sendMessage("You awnser with the true roll of: " + showToOtherPlayer);
    }

    static void playerSaysTheyRolledOnline(String playerName, int show, EchoClientHandler echoClientHandler) {
        echoClientHandler.sendMessage(playerName + " says they rolled " + show);
    }

    static void doYouBelieveOnline(String name, String playerName, EchoClientHandler echoClientHandler) {
        echoClientHandler.sendMessage(name + " do you believe that " + playerName + " rolled this?");
    }

    static void pressEnterToRollOnline(String name, EchoClientHandler echoClientHandler) throws IOException {
        echoClientHandler.sendMessage(name + " press Enter to roll!");
        echoClientHandler.sendMessage("...");
        echoClientHandler.readMessage();
    }

    static void turnChoiceRollOnline(String name, int turnRoll, EchoClientHandler echoClientHandler) {
        echoClientHandler.sendMessage(name + " rolled " + turnRoll);
    }

    static void isOutMessage(String name, EchoClientHandler echoClientHandler) {
        println(echoClientHandler.sendMessage("Player " + name + " hit 0 health, and is out!"));
    }

    static void gameWonMessage(String name, int health, EchoClientHandler echoClientHandler) {
        println(echoClientHandler.sendMessage("The Game is over! The winner is " + name + " with " + health + " health!"));
    }

    static void connectionStopped() {
        println("Connection has been stopped!");
    }

    static void canConnectNow() {
        println("Online players can connect now!");
    }

    static String youAreConnected(String name) {
        return "Hello " + name + ". You are connected and ready to play!";
    }

}
