package meyer2;

import java.util.Random;

/**
 *
 * @author Rasmus2
 */
public class OnlinePlayer extends PlayerCtrlImpl {

    private static final Random RND = new Random();
    private String name;
    private int health = 6;
    private int roll;
    private int showToOtherPlayer;
    private String ip;
    private EchoClientHandler echoClientHandler;

    public OnlinePlayer(EchoClientHandler echoClientHandler) {
        this.echoClientHandler = echoClientHandler;
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

}
