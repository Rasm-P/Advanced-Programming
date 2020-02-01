package dicegame;

import java.util.Random;

/**
 *
 * @author Rasmus2
 */
public class PlayerCtrl {

    private static Random rnd = new Random();
    private String name;
    private int health = 6;
    private int roll;
    private int showToOtherPlayer;

    void takeTurn() {
        String[] choices = {"show", "bluff"};
        TextUI.println("It's your turn " + name + ", your health is " + health + "! Press Enter to roll!");
        TextUI.getString();
        int d1 = rnd.nextInt(6) + 1;
        int d2 = rnd.nextInt(6) + 1;
        roll = d1 * 10 + d2;
        TextUI.println(name + " rolled " + d1 + " and " + d2 + ", that is: " + d1 + d2);
        if (d1 == 1 && d2 == 2) {
            TextUI.println("That is Meyer!");
        } else if (d1 == 1 && d2 == 3) {
            TextUI.println("That is Lille-meyer!");
        } else if (d1 == d2) {
            TextUI.println("That is a pair of: " + d1 + d2);
        }
        int answer = TextUI.choice(choices);
        if (answer == 0) {
            showToOtherPlayer = d1 * 10 + d2;
            TextUI.println("You awnser with the true roll of: " + d1 + d2);
        } else {
            TextUI.println("Make your buff!");
            int bluff = TextUI.getInteger();
            showToOtherPlayer = bluff;
            TextUI.println("You awnser with a bluff of: " + bluff);
        }
        for (int i = 0; i < 100; ++i) {
            System.out.println();
        }
    }

    int turnChoice(String playerName, int show, int roll) {
        String[] choices = {"yes", "no"};
        if (show != 0) {
            TextUI.println(name + " do you believe that " + playerName + " rolled this?");
            int answer = TextUI.choice(choices);
            if (answer == 0) {
                TextUI.println(name + " press Enter to roll!");
                TextUI.getString();
                int d1 = rnd.nextInt(6) + 1;
                int d2 = rnd.nextInt(6) + 1;
                TextUI.println("You rolled " + d1 + d2);
                if (d1 * 10 + d2 == show) {
                    TextUI.println("You both rolled the same!");
                    return 0;
                } else if (d1 == 1 && d2 == 2) {
                    TextUI.println("Meyer! You rolled higher than " + playerName + ", who lose -1 health");
                    return 1;
                } else if (d1 == 1 && d2 == 3 && show != 12) {
                    TextUI.println("Lille-meyer! You rolled higher than " + playerName + ", who lose -1 health");
                    return 1;
                } else if (d1 == d2 && String.valueOf(show).charAt(0) != String.valueOf(show).charAt(1)) {
                    TextUI.println("A pair!, You rolled higher than " + playerName + ", who lose -1 health");
                    return 1;
                } else if (d1 == d2 && String.valueOf(show).charAt(0) == String.valueOf(show).charAt(1) && d1 * 10 + d2 < show) {
                    TextUI.println("A pair!, You rolled higher than " + playerName + ", who lose -1 health");
                    return 1;
                } else if (d1 * 10 + d2 > show && String.valueOf(show).charAt(0) != String.valueOf(show).charAt(1)) {
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
