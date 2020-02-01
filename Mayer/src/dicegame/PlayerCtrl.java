/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dicegame;

import java.util.Random;

/**
 *
 * @author Rasmus2
 */
public class PlayerCtrl {

    private static Random rnd = new Random();
    private String name;
    private int score = 6;
    private int roll;
    private int showToOtherPlayer;

    void takeTurn() {
        String[] choices = {"show", "bluff"};
        TextUI.println("It's your turn, " + name + " your score is " + score + "! Press Enter to roll!");
        TextUI.getString();
        int d1 = rnd.nextInt(6) + 1;
        int d2 = rnd.nextInt(6) + 1;
        roll = d1 * 10 + d2;
        TextUI.println(name + " rolled " + d1 + " and " + d2 + ", that is: " + d1 + d2);
        if (d1 == 1 && d2 == 1) {
            TextUI.println("Double 1's! 10 points to Griffindor!");
        }

        int answer = TextUI.choice(choices);
        if (answer == 0) {
            showToOtherPlayer = d1 * 10 + d2;
            TextUI.println("You awnser with the true roll of: " + d1 + d2);
        } else {
            TextUI.println("Make your buff!");
            int bluff = TextUI.getInteger();
            showToOtherPlayer = bluff;
            TextUI.println("You awnser with a bluf of: " + bluff);
        }
        for (int i = 0; i < 100; ++i) {
            System.out.println();
        }
    }

    int turnChoice(PlayerCtrl player) {
        String[] choices = {"yes", "no"};
        if (player.getShowToOtherPlayer() != 0) {
            TextUI.println(name + " do you believe that " + player.getName() + " rolled this?");
            int answer = TextUI.choice(choices);
            if (answer == 0) {
                TextUI.println("Press Enter to roll!");
                TextUI.getString();
                int d1 = rnd.nextInt(6) + 1;
                int d2 = rnd.nextInt(6) + 1;
                TextUI.println("You rolled " + d1 + d2);
                if (d1 * 10 + d2 > player.getShowToOtherPlayer()) {
                    TextUI.println("You rolled higher than " + player.getName() + ", and they lose a point");
                    return 1;
                } else if (d1 * 10 + d2 < player.getShowToOtherPlayer()) {
                    TextUI.println("You rolled lower than " + player.getName() + ", and you lose a point");
                    score = score - 1;
                    return 0;
                } else {
                    TextUI.println("You both rolled the same!");
                    return 0;
                }
            } else {
                if (player.getShowToOtherPlayer() == player.getRoll()) {
                    TextUI.println("You were incorrect. It was true and you lose a point");
                    score = score - 1;
                    return 0;
                } else {
                    TextUI.println("You were correct. It was a bluff and " + player.getName() + " lose a point");
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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
