package meyer2;

import java.util.Random;

/**
 *
 * @author Rasmus2
 */
public class AI extends PlayerCtrlImpl {

    private static final Random RND = new Random();
    private String name;
    private int health = 6;
    private int roll;
    private int showToOtherPlayer;

    public AI() {

    }

    @Override
    public void takeTurn() {
        TextUI.itIsPlayerTurn(name, health);
        int d1 = RND.nextInt(6) + 1;
        int d2 = RND.nextInt(6) + 1;
        roll = PointUtil.rollSum(d1, d2);
        int answer = RND.nextInt(2);
        if (answer == 0) {
            showToOtherPlayer = roll;
        } else {
            int bluff = PointUtil.rollSum(RND.nextInt(6) + 1, RND.nextInt(6) + 1);
            showToOtherPlayer = bluff;
        }
    }

    @Override
    public int turnChoice(String playerName, int show, int roll) {
        int turnRoll;
        TextUI.playerSaysTheyRolled(playerName, show);
        if (show != 0) {
            TextUI.doYouBelieve(name, playerName);
            int answer = RND.nextInt(2);
            if (answer == 0) {
                TextUI.yesIDoBelieve(name);
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
                TextUI.noIDoBelieve(name);
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

}
