/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meyer4;

import java.net.*;
import java.io.*;

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
        Socket socket = serverSocket.accept();
        EchoClientHandler textSocket = new EchoClientHandler(socket);
        new Thread(textSocket).start();

        return textSocket;
    }
}
