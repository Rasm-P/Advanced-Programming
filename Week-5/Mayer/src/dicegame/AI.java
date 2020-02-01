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
public class AI {
    
    private static Random rnd = new Random();
    private String name = "God";
    private int score = 6;
    
    void takeTurn() {
        TextUI.println("It's God's turn!");
        int bank = 0;
        while(true) {
            int d1 = rnd.nextInt(6)+1;
            int d2 = rnd.nextInt(6)+1;
            TextUI.println(name + " rolled " + d1 + " and " + d2);
            if (d1 == 1 && d2 == 1){
                score += 10;
                TextUI.println("Double 1's! 10 points to Griffindor!");
            }
            else if (d1 == 1 || d2 == 1) {
                TextUI.println("God rolled a 1. " + name + "'s turn is over!\n");
                return;
            }
            else if (d1 == d2) {
                d1 *= 2;
                d2 *= 2;
                TextUI.println("A pair! Double points!");
            }
            bank += d1 + d2;
            TextUI.println("This turn God have rolled " + bank + " so far.");
            if (bank < 21){
                // continue
            } 
            else {
                score += bank;
                TextUI.println("God have stored " + bank + " to " + name + "'s score. His total score is: " + score + "!\n");
                return;
            }
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
}
