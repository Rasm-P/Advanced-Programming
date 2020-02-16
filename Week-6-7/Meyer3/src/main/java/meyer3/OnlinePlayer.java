package meyer3;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rasmus2
 */
public class OnlinePlayer extends PlayerCtrlImpl {

    private static final Random RND = new Random();
    private String name;
    private int health = 1;
    private int roll;
    private int showToOtherPlayer;
    private String ip;
    private final EchoClientHandler echoClientHandler;

    public OnlinePlayer(EchoClientHandler echoClientHandler) {
        this.echoClientHandler = echoClientHandler;
    }

    @Override
    public void takeTurn() {
        try {
            String[] choices = {"show", "bluff"};

            echoClientHandler.sendMessage("It's your turn " + name + ", your health is " + health + "! Press Enter to roll!");
            echoClientHandler.sendMessage("...");
            echoClientHandler.readMessage();
            //TextUI.itIsYouTurn(name, health);

            int d1 = RND.nextInt(6) + 1;
            int d2 = RND.nextInt(6) + 1;
            roll = PointUtil.rollSum(d1, d2);

            echoClientHandler.sendMessage(name + " you rolled " + roll);
            if (roll == 21) {
                echoClientHandler.sendMessage("That is Meyer!");
            } else if (roll == 31) {
                echoClientHandler.sendMessage("That is Lille-meyer!");
            } else if (d1 == d2) {
                echoClientHandler.sendMessage("That is a pair of: " + d1 + d2);
            }
            //TextUI.youRolled(name, roll, d1, d2);

            int answer = TextUI.choiceOnline(choices, echoClientHandler);
            //int answer = TextUI.choice(choices);

            if (answer == 0) {
                showToOtherPlayer = roll;

                echoClientHandler.sendMessage("You awnser with the true roll of: " + roll);
                //TextUI.answerTrue(showToOtherPlayer);

            } else {

                showToOtherPlayer = TextUI.answerBluffOnline(echoClientHandler);
                //showToOtherPlayer = TextUI.answerBluff();

            }
        } catch (IOException ex) {
            TextUI.println(ex.getMessage());
        }
    }

    @Override
    public int turnChoice(String playerName, int show, int roll) {
        int turnRoll;
        String[] choices = {"yes", "no"};

        echoClientHandler.sendMessage(playerName + " says they rolled " + show);
        //TextUI.playerSaysTheyRolled(playerName, show);

        if (show != 0) {

            try {
                echoClientHandler.sendMessage(name + " do you believe that " + playerName + " rolled this?");
                //TextUI.doYouBelieve(name, playerName);

                int answer = TextUI.choiceOnline(choices, echoClientHandler);
                //int answer = TextUI.choice(choices);

                if (answer == 0) {

                    echoClientHandler.sendMessage(name + " press Enter to roll!");
                    echoClientHandler.sendMessage("...");
                    echoClientHandler.readMessage();
                    //TextUI.pressEnterToRoll(name);

                    int d1 = RND.nextInt(6) + 1;
                    int d2 = RND.nextInt(6) + 1;
                    turnRoll = PointUtil.rollSum(d1, d2);

                    echoClientHandler.sendMessage(name + " rolled " + turnRoll);
                    //TextUI.turnChoiceRoll(name, turnRoll);

                    int turnPointsYes = PointUtil.turnPointsYesChoiceOnline(turnRoll, show, name, playerName, d1, d2, echoClientHandler);
                    if (turnPointsYes == -1) {
                        health = health - 1;
                        return 0;
                    } else {
                        return turnPointsYes;
                    }
                } else {
                    int turnPointsNo = PointUtil.turnPointsNoChoiceOnline(roll, show, name, playerName, echoClientHandler);
                    if (turnPointsNo == -1) {
                        health = health - 1;
                        return 0;
                    } else {
                        return turnPointsNo;
                    }
                }
            } catch (IOException ex) {
                TextUI.println(ex.getMessage());
            }
        }
        return 0;
    }

    @Override
    public void isOut() {
        try {
            TextUI.println(echoClientHandler.sendMessage("Player " + name + " hit 0 health, and is out!"));
            echoClientHandler.closeConnection();
        } catch (IOException ex) {
            TextUI.println(ex.getMessage());
        }
    }

    @Override
    public void gameWon() {
        TextUI.println(echoClientHandler.sendMessage("Game over! The winner is " + name + " with " + health + " health!"));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int score) {
        this.health = score;
    }

    @Override
    void init() {
        setName(TextUI.getString());
    }

    @Override
    public int getRoll() {
        return roll;
    }

    @Override
    public void setRoll(int roll) {
        this.roll = roll;
    }

    @Override
    public int getShowToOtherPlayer() {
        return showToOtherPlayer;
    }

    @Override
    public void setShowToOtherPlayer(int showToOtherPlayer) {
        this.showToOtherPlayer = showToOtherPlayer;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public EchoClientHandler getEchoClientHandler() {
        return echoClientHandler;
    }

}
