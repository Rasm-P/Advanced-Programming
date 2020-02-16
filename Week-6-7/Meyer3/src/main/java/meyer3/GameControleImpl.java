/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meyer3;

import interfaces.GameControle;
import java.io.IOException;
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
        while (num != 0) {
            pageBreak(0);
            playerList.get(num).takeTurn();
            pageBreak(0);
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
            playerList.get(number).isOut();
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
        String[] choices = {"PVP", "AI", "Online Host", "Online Client"};
        int answer = TextUI.choice(choices);
        switch (answer) {
            case 0: {
                TextUI.howManyPlayers();
                int amount = TextUI.getInteger();
                for (int i = 1; i < amount + 1; i++) {
                    Player player = new Player();
                    TextUI.whatPlayerName(i);
                    player.init();
                    playerList.add(player);
                }
                num = RND.nextInt(playerList.size());
                break;
            }
            case 1: {
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
                break;
            }
            case 2:
                EchoMultiServer server = new EchoMultiServer();
                try {
                    Player p = new Player();
                    TextUI.whatPlayerName(1);
                    p.init();
                    playerList.add(p);
                    TextUI.howManyPlayers();
                    int amount = TextUI.getInteger();
                    server.start(5555);
                    TextUI.println("Online players can connect now!");
                    for (int i = 1; i < amount + 1; i++) {
                        OnlinePlayer player = new OnlinePlayer(server.newClientHandler());
                        player.setName(player.getEchoClientHandler().readMessage());
                        playerList.add(player);
                        TextUI.println(player.getEchoClientHandler().sendMessage("Hello " + player.getName() + ". You are connected and ready to play!"));
                    }
                    num = RND.nextInt(playerList.size());
                } catch (IOException ex) {
                    TextUI.println(ex.getMessage());
                }
                break;
            case 3:
                try {
                    ClientGameControle GC = ClientGameControle.getInstance();
                    GC.playGame();
                } catch (IOException ex) {
                    TextUI.println(ex.getMessage());
                }
                break;
        }
    }

    @Override
    public void gameFinished() {
        playerList.get(0).gameWon();
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
