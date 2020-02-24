package meyer4;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rasmus2
 */
public class OnlinePlayer extends PlayerCtrlAbs {

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
            TextUI.itIsYouTurnOnline(name, health, echoClientHandler);
            int d1 = RND.nextInt(6) + 1;
            int d2 = RND.nextInt(6) + 1;
            roll = PointUtil.rollSum(d1, d2);
            TextUI.youRolledOnline(name, roll, d1, d2, echoClientHandler);
            int answer = TextUI.choiceOnline(choices, echoClientHandler);
            if (answer == 0) {
                showToOtherPlayer = roll;
                TextUI.answerTrueOnline(showToOtherPlayer, echoClientHandler);
            } else {
                showToOtherPlayer = TextUI.answerBluffOnline(echoClientHandler);
            }
        } catch (IOException ex) {
            TextUI.println(ex.getMessage());
        }
    }

    @Override
    public int turnChoice(String playerName, int show, int roll) {
        int turnRoll;
        String[] choices = {"yes", "no"};
        TextUI.playerSaysTheyRolledOnline(playerName, show, echoClientHandler);
        if (show != 0) {
            try {
                TextUI.doYouBelieveOnline(name, playerName, echoClientHandler);
                int answer = TextUI.choiceOnline(choices, echoClientHandler);
                if (answer == 0) {
                    TextUI.pressEnterToRollOnline(name, echoClientHandler);
                    int d1 = RND.nextInt(6) + 1;
                    int d2 = RND.nextInt(6) + 1;
                    turnRoll = PointUtil.rollSum(d1, d2);
                    TextUI.turnChoiceRollOnline(name, turnRoll, echoClientHandler);
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
            TextUI.isOutMessage(name, echoClientHandler);
            echoClientHandler.close();
        } catch (IOException ex) {
            TextUI.println(ex.getMessage());
        }
    }

    @Override
    public void gameWon() {
        TextUI.gameWonMessage(name, health, echoClientHandler);
        try {
            echoClientHandler.close();
        } catch (IOException ex) {
            Logger.getLogger(OnlinePlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
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
