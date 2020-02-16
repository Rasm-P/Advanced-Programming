/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meyer2;

import interfaces.PlayerCtrl;
import java.util.Random;

/**
 *
 * @author rasmu
 */
public abstract class PlayerCtrlImpl implements PlayerCtrl {

    private static final Random RND = new Random();
    private String name;
    private int health = 6;
    private int roll;
    private int showToOtherPlayer;

    public PlayerCtrlImpl() {

    }

    @Override
    abstract public void takeTurn();

    @Override
    abstract public int turnChoice(String playerName, int show, int roll);

    @Override
    abstract public void isOut();

    @Override
    abstract public void gameWon();

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
