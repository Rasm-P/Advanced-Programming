package dicegame;

import java.util.Random;

/**
 *
 * @author Rasmus2
 */
public class AI {

    private static Random rnd = new Random();
    private String name = "God";
    private int health = 6;
    private int roll;
    private int showToOtherPlayer;

    void takeTurn() {
        TextUI.println("It's " + name + "'s turn, their health is " + health + "!");
        int d1 = rnd.nextInt(6) + 1;
        int d2 = rnd.nextInt(6) + 1;
        roll = d1 * 10 + d2;
        int answer = rnd.nextInt(2);
        if (answer == 0) {
            showToOtherPlayer = d1 * 10 + d2;
        } else {
            int bluff = rnd.nextInt(6) + 1 * 10 + rnd.nextInt(6) + 1;
            showToOtherPlayer = bluff;
        }
        for (int i = 0; i < 5; ++i) {
            System.out.println();
        }
    }

    int turnChoice(String playername, int show, int playerroll) {
        if (show != 0) {
            TextUI.println(name + " do you believe that " + playername + " rolled this?");
            int answer = rnd.nextInt(2);
            if (answer == 0) {
                TextUI.println(name + ": Yes i do!");
                int d1 = rnd.nextInt(6) + 1;
                int d2 = rnd.nextInt(6) + 1;
                TextUI.println(name + " rolled " + d1 + d2);
                if (d1 * 10 + d2 == show) {
                    TextUI.println("You both rolled the same!");
                    return 0;
                } else if (d1 == 1 && d2 == 2) {
                    TextUI.println("Meyer! " + name + " rolled higher than " + playername + ", who lose -1 health");
                    return 1;
                } else if (d1 == 1 && d2 == 3 && show != 12) {
                    TextUI.println("Lille-meyer! " + name + " rolled higher than " + playername + ", who lose -1 health");
                    return 1;
                } else if (d1 == d2 && String.valueOf(show).charAt(0) != String.valueOf(show).charAt(1)) {
                    TextUI.println("A pair!, " + name + " rolled higher than " + playername + ", who lose -1 health");
                    return 1;
                } else if (d1 == d2 && String.valueOf(show).charAt(0) == String.valueOf(show).charAt(1) && d1 * 10 + d2 < show) {
                    TextUI.println("A pair!, " + name + " rolled higher than " + playername + ", who lose -1 health");
                    return 1;
                } else if (d1 * 10 + d2 > show && String.valueOf(show).charAt(0) != String.valueOf(show).charAt(1)) {
                    TextUI.println(name + " rolled higher than " + playername + ", who lose -1 health");
                    return 1;
                } else {
                    TextUI.println(name + " rolled lower than " + playername + " who rolled " + show + ", and " + name + " lose -1 health");
                    health = health - 1;
                    return 0;
                }
            } else {
                TextUI.println(name + ": No i dont, it is a bluff!");
                if (show == playerroll) {
                    TextUI.println(name + " was incorrect. It was true and " + name + " lose -1 health");
                    health = health - 1;
                    return 0;
                } else {
                    TextUI.println(name + " was correct. It was a bluff and " + playername + " loses -1 health");
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
