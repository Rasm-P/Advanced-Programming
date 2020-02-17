package meyer3;

import java.net.*;
import java.io.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rasmu
 */
public class GreetClient {

    private Socket serverSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        serverSocket = new Socket(ip, port);
        out = new PrintWriter(serverSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
    }

    public String readMessage() throws IOException {
        String resp = in.readLine();
        return resp;
    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        return msg;
    }

    public Boolean isConnected() {
        return serverSocket.isConnected();
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        serverSocket.close();
        TextUI.connectionStopped();
    }
}
