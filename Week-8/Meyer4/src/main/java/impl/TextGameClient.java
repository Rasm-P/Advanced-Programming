package impl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import textIO.SysTextIO;
import textIO.TextUI;
import interfaces.ITextIO;
import java.io.BufferedInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author rasmu
 */
public class TextGameClient implements Runnable {

    private static TextGameClient instance;
    private final String host;
    private final int port;
    private final ITextIO io;

    public TextGameClient() {
        this.io = new SysTextIO();
        io.put("Please enter server ip-adress: ");
        this.host = io.get();
        io.put("\nPlease enter server port: ");
        this.port = io.getInteger(0, 65535);
    }

    public static TextGameClient getInstance() {
        if (instance == null) {
            instance = new TextGameClient();
        }
        return instance;
    }

    @Override
    public void run() {

        Socket s = null;
        try {
            s = new Socket(host, port);
            DataInput in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
            DataOutput out = new DataOutputStream(s.getOutputStream());
            String cmd = in.readUTF();
            TextUI.whatIsClientName();
            while (!"close".equalsIgnoreCase(cmd)) {
                if ("put".equalsIgnoreCase(cmd)) {
                    String str = in.readUTF();
                    io.put(str);
                } else if ("clear".equalsIgnoreCase(cmd)) {
                    io.clear();
                } else if ("get".equalsIgnoreCase(cmd)) {
                    String res = io.get();
                    out.writeUTF(res);
                } else {
                    throw new RuntimeException("Unknown protocol command: " + cmd);
                }
                System.out.println("");
                cmd = in.readUTF();
            }
            io.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (IOException ex) {
                }
            }
        }
    }

}
