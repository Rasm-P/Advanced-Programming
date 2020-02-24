package textIO;

import impl.EchoClientHandlerImpl;
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

    public static int getIntegerOnline(EchoClientHandlerImpl eco) throws IOException {
        while (true) {
            String sNum = eco.get();
            try {
                return Integer.parseInt(sNum);
            } catch (NumberFormatException e) {
                eco.put("Please enter an integer! \n: ");
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

    public static int choiceOnline(String[] choices, EchoClientHandlerImpl eco) throws IOException {
        if (choices == null || choices.length == 0) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < choices.length; ++i) {
            eco.put("press " + i + " to " + choices[i]);
        }
        eco.put("Please choose: ");
        return getIntegerOnline(eco);
    }

    public static void gameWinner(String name, int health) {
        println("Game over!");
        println("The winner is " + name + " with " + health + " health!");
    }

    public static void whatAiName(int i) {
        println("What's the name of AI " + i + "?");
    }

    public static void howManyAi() {
        println("How many AI's?");
    }

    public static void whatPlayerName(int i) {
        println("What's your name, player " + i + "?");
    }

    public static void howManyPlayers() {
        println("How many players?");
    }

    public static void welcome() {
        println("Welcome to the game");
    }

    public static void playerIsOut(String name) {
        println("Player " + name + " hit 0 health, and is out!");
    }

    public static void playerSaysTheyRolled(String playerName, int show) {
        println(playerName + " says they rolled " + show);
    }

    public static void itIsYouTurn(String name, int health) {
        println("It's your turn " + name + ", your health is " + health + "! Press Enter to roll!");
        TextUI.getString();
    }

    public static void youRolled(String name, int roll, int d1, int d2) {
        println(name + " you rolled " + roll);
        if (roll == 21) {
            TextUI.println("That is Meyer!");
        } else if (roll == 31) {
            TextUI.println("That is Lille-meyer!");
        } else if (d1 == d2) {
            TextUI.println("That is a pair of: " + d1 + d2);
        }
    }

    public static void answerTrue(int roll) {
        println("You awnser with the true roll of: " + roll);
    }

    public static int answerBluff() {
        TextUI.println("Make your buff!");
        int bluff = TextUI.getInteger();
        TextUI.println("You awnser with a bluff of: " + bluff);
        return bluff;
    }

    public static int answerBluffOnline(EchoClientHandlerImpl eco) throws IOException {
        eco.put("Make your buff!");
        int bluff = TextUI.getIntegerOnline(eco);
        eco.put("You awnser with a bluff of: " + bluff);
        return bluff;
    }

    public static void doYouBelieve(String name, String playerName) {
        println(name + " do you believe that " + playerName + " rolled this?");
    }

    public static void pressEnterToRoll(String name) {
        println(name + " press Enter to roll!");
        getString();
    }

    public static void turnChoiceRoll(String name, int turnRoll) {
        println(name + " rolled " + turnRoll);
    }

    public static void rolledTheSame() {
        println("You both rolled the same!");
    }

    public static void rolledMeyer(String name, String playerName) {
        println("Meyer! " + name + " rolled higher than " + playerName + ", who lose -1 health");
    }

    public static void rolledLilleMeyer(String name, String playerName) {
        println("Lille-meyer! " + name + " rolled higher than " + playerName + ", who lose -1 health");
    }

    public static void rolledPair1(String name, String playerName) {
        println("A pair!, " + name + " rolled higher than " + playerName + ", who lose -1 health");
    }

    public static void rolledHigher(String name, String playerName) {
        println(name + " rolled higher than " + playerName + ", who lose -1 health");
    }

    public static void rolledLower(String name, String playerName, int show) {
        println(name + " rolled lower than " + playerName + " who rolled " + show + ", and " + name + " lose -1 health");
    }

    public static void wasIncorrect(String name) {
        println(name + " was incorrect. It was true and " + name + " lose -1 health");
    }

    public static void wasCorrect(String name, String playerName) {
        println(name + " was correct. It was a bluff and " + playerName + " loses -1 health");
    }

    public static void itIsPlayerTurn(String name, int health) {
        println("It's " + name + "'s turn, their health is " + health + "!");
    }

    public static void yesIDoBelieve(String name) {
        println(name + ": Yes i do!");
    }

    public static void noIDoBelieve(String name) {
        println(name + ": No i dont, it is a bluff!");
    }

    public static void enterIp() {
        println("Please enter host IP:");
    }

    public static void whatIsClientName() {
        println("What's your name?");
    }

    public static void itIsYouTurnOnline(String name, int health, EchoClientHandlerImpl echoClientHandler) throws IOException {
        echoClientHandler.put("It's your turn " + name + ", your health is " + health + "! Press Enter to roll!");
        echoClientHandler.get();
    }

    public static void youRolledOnline(String name, int roll, int d1, int d2, EchoClientHandlerImpl echoClientHandler) {
        echoClientHandler.put(name + " you rolled " + roll);
        if (roll == 21) {
            echoClientHandler.put("That is Meyer!");
        } else if (roll == 31) {
            echoClientHandler.put("That is Lille-meyer!");
        } else if (d1 == d2) {
            echoClientHandler.put("That is a pair of: " + d1 + d2);
        }
    }

    public static void answerTrueOnline(int showToOtherPlayer, EchoClientHandlerImpl echoClientHandler) {
        echoClientHandler.put("You awnser with the true roll of: " + showToOtherPlayer);
    }

    public static void playerSaysTheyRolledOnline(String playerName, int show, EchoClientHandlerImpl echoClientHandler) {
        echoClientHandler.put(playerName + " says they rolled " + show);
    }

    public static void doYouBelieveOnline(String name, String playerName, EchoClientHandlerImpl echoClientHandler) {
        echoClientHandler.put(name + " do you believe that " + playerName + " rolled this?");
    }

    public static void pressEnterToRollOnline(String name, EchoClientHandlerImpl echoClientHandler) throws IOException {
        echoClientHandler.put(name + " press Enter to roll!");
        echoClientHandler.get();
    }

    public static void turnChoiceRollOnline(String name, int turnRoll, EchoClientHandlerImpl echoClientHandler) {
        echoClientHandler.put(name + " rolled " + turnRoll);
    }

    public static void isOutMessage(String name, EchoClientHandlerImpl echoClientHandler) {
        println(echoClientHandler.put("Player " + name + " hit 0 health, and is out!"));
    }

    public static void gameWonMessage(String name, int health, EchoClientHandlerImpl echoClientHandler) {
        println(echoClientHandler.put("The Game is over! The winner is " + name + " with " + health + " health!"));
    }

    public static void connectionStopped() {
        println("Connection has been stopped!");
    }

    public static void canConnectNow() {
        println("Online players can connect now!");
    }

    public static String youAreConnected(String name) {
        return "Hello " + name + ". You are connected and ready to play!";
    }

}
