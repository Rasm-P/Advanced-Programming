/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meyerClient;

import java.io.IOException;
import java.net.SocketException;

/**
 *
 * @author rasmu
 */
public class GameControle {

    private static GameControle instance;
    GreetClient client = new GreetClient();

    private GameControle() {
    }

    public static GameControle getInstance() {
        if (instance == null) {
            instance = new GameControle();
        }
        return instance;
    }

    public void playGame() throws IOException {
        try {
            int port = 5555;
            String IP;
            //"192.168.1.6"
            TextUI.println("Please enter host IP:");
            IP = TextUI.getString();
            client.startConnection(IP, port);
            Player player = new Player();
            TextUI.println("What's your name?");
            player.init();
            client.sendMessage(player.getName());
            while (client.isConnected()) {
                String message = client.readMessage();
                if (message != null) {
                    TextUI.println(message);
                    if ("...".equals(message)) {
                        client.sendMessage(TextUI.getString());
                    }
                }
            }
            client.stopConnection();
        } catch (IOException ex) {
            client.stopConnection();
        }

    }
}
