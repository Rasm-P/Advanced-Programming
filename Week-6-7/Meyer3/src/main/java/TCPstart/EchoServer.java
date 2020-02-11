/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCPstart;

import java.net.*;
import java.io.*;

/**
 *
 * @author rasmu
 */
public class EchoServer {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            if (".".equals(inputLine)) {
                out.println("good bye");
                break;
            }
            out.println(inputLine);
        }
    }
    public static void main(String[] args) throws IOException {
        EchoServer server=new EchoServer();
        server.start(4444);
    }
}
