package meyerClient;

import TCPstart.*;
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
    
    public String readMessage() throws IOException{
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
        TextUI.println("Connection has been stopped!");
    }
    
    public static void main(String[] args) throws IOException {     
        int port = 5555;
        String IP = "192.168.1.6";
        
        GreetClient client1 = new GreetClient();
        client1.startConnection(IP, port);
        String msg1 = client1.sendMessage("hello");
        String msg2 = client1.sendMessage("world");
        String terminate = client1.sendMessage(".");

        System.out.println(msg1);
        System.out.println(msg2);
        System.out.println(terminate);
        
        GreetClient client2 = new GreetClient();
        client2.startConnection(IP, port);
        String msg3 = client2.sendMessage("hello");
        String msg4 = client2.sendMessage("world");
        String terminate2 = client2.sendMessage(".");
        
        System.out.println(msg3);
        System.out.println(msg4);
        System.out.println(terminate2);
    }
}
