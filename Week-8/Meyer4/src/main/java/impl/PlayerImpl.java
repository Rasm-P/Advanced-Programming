package impl;

import textIO.TextUI;
import interfaces.PlayerCtrlAbs;
import java.util.Random;

/**
 *
 * @author Rasmus2
 */
public class PlayerImpl extends PlayerCtrlAbs {

    private static final Random RND = new Random();
    private String name;
    private int health = 6;
    private int roll;
    private int showToOtherPlayer;

    public PlayerImpl() {

    }

    @Override
    public void takeTurn() {
        String[] choices = {"show", "bluff"};
        TextUI.itIsYouTurn(name, health);
        int d1 = RND.nextInt(6) + 1;
        int d2 = RND.nextInt(6) + 1;
        roll = PointUtil.rollSum(d1, d2);
        TextUI.youRolled(name, roll, d1, d2);
        int answer = TextUI.choice(choices);
        if (answer == 0) {
            showToOtherPlayer = roll;
            TextUI.answerTrue(showToOtherPlayer);
        } else {
            showToOtherPlayer = TextUI.answerBluff();
        }
    }

    @Override
    public int turnChoice(String playerName, int show, int roll) {
        int turnRoll;
        String[] choices = {"yes", "no"};
        TextUI.playerSaysTheyRolled(playerName, show);
        if (show != 0) {
            TextUI.doYouBelieve(name, playerName);
            int answer = TextUI.choice(choices);
            if (answer == 0) {
                TextUI.pressEnterToRoll(name);
                int d1 = RND.nextInt(6) + 1;
                int d2 = RND.nextInt(6) + 1;
                turnRoll = PointUtil.rollSum(d1, d2);
                TextUI.turnChoiceRoll(name, turnRoll);
                int turnPointsYes = PointUtil.turnPointsYesChoice(turnRoll, show, name, playerName, d1, d2);
                if (turnPointsYes == -1) {
                    health = health - 1;
                    return 0;
                } else {
                    return turnPointsYes;
                }
            } else {
                int turnPointsNo = PointUtil.turnPointsNoChoice(roll, show, name, playerName);
                if (turnPointsNo == -1) {
                    health = health - 1;
                    return 0;
                } else {
                    return turnPointsNo;
                }
            }
        } else {
            return 0;
        }
    }

    @Override
    public void isOut() {
        TextUI.playerIsOut(name);
    }

    @Override
    public void gameWon() {
        TextUI.gameWinner(name, health);
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
    public void init() {
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

}
