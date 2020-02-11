/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meyer2;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rasmu
 */
public class EchoMultiServer {

    private ServerSocket serverSocket;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void stop() throws IOException {
        serverSocket.close();
    }
    
    public EchoClientHandler newClientHandler() throws IOException {
        EchoClientHandler e = new EchoClientHandler(serverSocket.accept());
        e.start();
        return e;
    }

    
    public static void main(String[] args) throws IOException {
        EchoMultiServer server=new EchoMultiServer();
        server.start(5555);
    }
}
