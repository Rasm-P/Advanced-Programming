package meyerClient;

import java.util.Random;

/**
 *
 * @author Rasmus2
 */
public class ClientPlayer {

    private static final Random RND = new Random();
    private String name;
    private int health = 6;
    private int roll;
    private int showToOtherPlayer;

    public ClientPlayer() {

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

    public void setHealth(int health) {
        this.health = health;
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
    
    void init() {
        setName(TextUI.getString());
    }
}
