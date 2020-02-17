/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meyer3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author rasmu
 */
public class EchoClientHandler extends Thread {

    private final Socket clientSocket;
    private final PrintWriter out;
    private final BufferedReader in;

    public EchoClientHandler(Socket socket) throws IOException {
        this.clientSocket = socket;
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

    }

    public String readMessage() throws IOException {
        String input = "";
        while ("".equals(input) || input == null) {
            input = in.readLine();
        }
        return input;
    }

    public String sendMessage(String msg) {
        out.println(msg);
        return msg;
    }

    public void closeConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        TextUI.connectionStopped();
    }
}
