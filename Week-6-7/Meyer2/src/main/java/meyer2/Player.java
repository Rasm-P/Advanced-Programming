package meyer2;

import java.util.Random;

/**
 *
 * @author Rasmus2
 */
public class Player extends PlayerCtrlImpl {

    private static final Random RND = new Random();
    private String name;
    private int health = 2;
    private int roll;
    private int showToOtherPlayer;

    public Player() {

    }

    @Override
    public void takeTurn() {
        String[] choices = {"show", "bluff"};
        TextUI.println("It's your turn " + name + ", your health is " + health + "! Press Enter to roll!");
        TextUI.getString();
        int d1 = RND.nextInt(6) + 1;
        int d2 = RND.nextInt(6) + 1;
        if (d1 > d2) {
            roll = d1 * 10 + d2;
        } else {
            roll = d2 * 10 + d1;
        }
        TextUI.println(name + " you rolled " + roll);
        if (roll == 21) {
            TextUI.println("That is Meyer!");
        } else if (roll == 31) {
            TextUI.println("That is Lille-meyer!");
        } else if (d1 == d2) {
            TextUI.println("That is a pair of: " + d1 + d2);
        }
        int answer = TextUI.choice(choices);
        if (answer == 0) {
            showToOtherPlayer = roll;
            TextUI.println("You awnser with the true roll of: " + roll);
        } else {
            TextUI.println("Make your buff!");
            int bluff = TextUI.getInteger();
            showToOtherPlayer = bluff;
            TextUI.println("You awnser with a bluff of: " + bluff);
        }
    }

    @Override
    public int turnChoice(String playerName, int show, int roll) {
        int turnRoll;
        String[] choices = {"yes", "no"};
        if (show != 0) {
            TextUI.println(name + " do you believe that " + playerName + " rolled this?");
            int answer = TextUI.choice(choices);
            if (answer == 0) {
                TextUI.println(name + " press Enter to roll!");
                TextUI.getString();
                int d1 = RND.nextInt(6) + 1;
                int d2 = RND.nextInt(6) + 1;
                if (d1 > d2) {
                    turnRoll = d1 * 10 + d2;
                } else {
                    turnRoll = d2 * 10 + d1;
                }
                TextUI.println("You rolled " + turnRoll);
                if (turnRoll == show) {
                    TextUI.println("You both rolled the same!");
                    return 0;
                } else if (turnRoll == 21) {
                    TextUI.println("Meyer! You rolled higher than " + playerName + ", who lose -1 health");
                    return 2;
                } else if (turnRoll == 31 && show != 21) {
                    TextUI.println("Lille-meyer! You rolled higher than " + playerName + ", who lose -1 health");
                    return 1;
                } else if (d1 == d2 && String.valueOf(show).charAt(0) != String.valueOf(show).charAt(1) && show != 21 && show != 31) {
                    TextUI.println("A pair!, You rolled higher than " + playerName + ", who lose -1 health");
                    return 1;
                } else if (d1 == d2 && String.valueOf(show).charAt(0) == String.valueOf(show).charAt(1) && turnRoll > show) {
                    TextUI.println("A pair!, You rolled higher than " + playerName + ", who lose -1 health");
                    return 1;
                } else if (turnRoll > show && String.valueOf(show).charAt(0) != String.valueOf(show).charAt(1) && show != 21 && show != 31) {
                    TextUI.println("You rolled higher than " + playerName + ", who lose -1 health");
                    return 1;
                } else {
                    TextUI.println("You rolled lower than " + playerName + " who rolled " + show + ", and you lose -1 health");
                    health = health - 1;
                    return 0;
                }
            } else {
                if (show == roll) {
                    TextUI.println("You were incorrect. It was true and you lose -1 health");
                    health = health - 1;
                    return 0;
                } else {
                    TextUI.println("You were correct. It was a bluff and " + playerName + " loses -1 health");
                    return 1;
                }
            }
        } else {
            return 0;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int score) {
        this.health = score;
    }

    void init() {
        setName(TextUI.getString());
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public int getShowToOtherPlayer() {
        return showToOtherPlayer;
    }

    public void setShowToOtherPlayer(int showToOtherPlayer) {
        this.showToOtherPlayer = showToOtherPlayer;
    }

}
