/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meyerClient;

import java.io.IOException;

/**
 *
 * @author rasmu
 */
public class ClientGameControle {

    private static ClientGameControle instance;
    GreetClient client = new GreetClient();

    private ClientGameControle() {
    }

    public static ClientGameControle getInstance() {
        if (instance == null) {
            instance = new ClientGameControle();
        }
        return instance;
    }

    public void playGame() throws IOException {
    
            int port = 5555;
            String IP;
            //"192.168.1.6"
            TextUI.println("Please enter host IP:");
            IP = TextUI.getString();
            client.startConnection(IP, port);
            ClientPlayer player = new ClientPlayer();
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
        }

    }

