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
        while (true) {
            pageBreak(0);
            playerList.get(num).takeTurn();
            pageBreak(0);
            TextUI.playerSaysTheyRolled(playerList.get(num));
            if (num == playerList.size() - 1) {
                managePoints(playerList.get(num), playerList.get(0));
                isOut(num);
                isOut(0);
                num = 0;
            } else {
                managePoints(playerList.get(num), playerList.get(num + 1));
                isOut(num + 1);
                isOut(num);
                num += 1;
            }
            pageBreak(5);
            if (playerList.size() == 1) {
                gameFinished();
                break;
            }
        }
    }

    @Override
    public void managePoints(PlayerCtrlImpl currentPlayer, PlayerCtrlImpl nextPlayers) {
        currentPlayer.setHealth(currentPlayer.getHealth() - nextPlayers.turnChoice(currentPlayer.getName(), currentPlayer.getShowToOtherPlayer(), currentPlayer.getRoll()));
    }

    @Override
    public void isOut(int number) {
        if (playerList.get(number).getHealth() <= 0) {
            TextUI.playerIsOut(playerList.get(number));
            playerList.remove(number);
            if (num == playerList.size() - 1) {
                num = 0;
            } else {
                num += 1;
            }
        }
    }

    @Override
    public void showIntro() {
        TextUI.welcome();
        String[] choices = {"PVP", "AI"};
        int answer = TextUI.choice(choices);
        if (answer == 0) {
            TextUI.howManyPlayers();
            int amount = TextUI.getInteger();
            for (int i = 1; i < amount + 1; i++) {
                Player player = new Player();
                TextUI.whatPlayerName(i);
                player.init();
                playerList.add(player);
            }
            num = RND.nextInt(playerList.size());
        } else {
            Player player = new Player();
            TextUI.whatPlayerName(1);
            player.init();
            playerList.add(player);
            TextUI.howManyAi();
            int amount = TextUI.getInteger();
            for (int i = 1; i < amount + 1; i++) {
                AI ai = new AI();
                TextUI.whatAiName(i);
                ai.init();
                playerList.add(ai);
            }
            num = 0;
        }
    }

    @Override
    public void gameFinished() {
        TextUI.gameWinner(playerList.get(0));
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
