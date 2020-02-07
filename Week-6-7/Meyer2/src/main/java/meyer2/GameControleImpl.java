/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meyer2;

import interfaces.GameControle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author rasmu
 */
public class GameControleImpl implements GameControle {

    private static final Random RND = new Random();
    private static GameControleImpl instance;
    private final List<PlayerCtrlImpl> playerList = new ArrayList();
    private int num;

    private GameControleImpl() {
    }

    public static GameControleImpl getInstance() {
        if (instance == null) {
            instance = new GameControleImpl();
        }
        return instance;
    }

    @Override
    public void playGame() {
        showIntro();
        num = RND.nextInt(playerList.size());
        while (true) {
            pageBreak(0);
            playerList.get(num).takeTurn();
            pageBreak(0);
            TextUI.println(playerList.get(num).getName() + " says they rolled " + playerList.get(num).getShowToOtherPlayer());
            if (num == playerList.size() - 1) {
                playerList.get(num).setHealth(playerList.get(num).getHealth() - playerList.get(0).turnChoice(playerList.get(num).getName(), playerList.get(num).getShowToOtherPlayer(), playerList.get(num).getRoll()));
                num = 0;
            } else {
                playerList.get(num).setHealth(playerList.get(num).getHealth() - playerList.get(num + 1).turnChoice(playerList.get(num).getName(), playerList.get(num).getShowToOtherPlayer(), playerList.get(num).getRoll()));
                num += 1;
            }
            if (playerList.get(num).getHealth() <= 0) {
                TextUI.println("Player " + playerList.get(num).getName() + " hit 0 health, and is out!");
                playerList.remove(num);
                if (num == playerList.size() - 1) {
                    num = 0;
                } else {
                    num += 1;
                }
            }
            pageBreak(5);
            if (playerList.size() == 1) {
                gameFinished();
                break;
            }
        }
    }

    @Override
    public void showIntro() {
        TextUI.println("Welcome to the game");
        String[] choices = {"PVP", "AI"};
        int answer = TextUI.choice(choices);
        if (answer == 0) {
            TextUI.println("How many players?");
            int amount = TextUI.getInteger();
            for (int i = 1; i < amount + 1; i++) {
                Player player = new Player();
                TextUI.println("What's your name, player " + i + "?");
                player.init();
                playerList.add(player);
            }
        } else {
            TextUI.println("How many AI's?");
            int amount = TextUI.getInteger();
            for (int i = 1; i < amount + 1; i++) {
                Player player = new Player();
                TextUI.println("What's the name of AI " + i + "?");
                player.init();
                playerList.add(player);
            }
        }
    }

    @Override
    public void gameFinished() {
        TextUI.println("Game over!");
        TextUI.println("The winner is " + playerList.get(0).getName() + " with " + playerList.get(0).getHealth() + " health!");
    }

    @Override
    public void pageBreak(int sleep) {
        try {
            Thread.sleep(sleep * 1000);
        } catch (InterruptedException ex) {
        }
        for (int i = 0; i < 100; ++i) {
            TextUI.println("");
        }
    }

}
