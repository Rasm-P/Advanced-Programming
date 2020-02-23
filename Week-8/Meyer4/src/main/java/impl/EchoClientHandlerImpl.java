/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impl;

import sync.SyncBox;
import interfaces.ITextIO;
import java.io.BufferedInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rasmu
 */
public class EchoClientHandlerImpl implements ITextIO, Runnable {

    private final Socket socket;
    private final SyncBox<String> cmdBox;
    private final SyncBox<String> putBox;
    private final SyncBox<String> getBox;

    public EchoClientHandlerImpl(Socket socket) {
        this.socket = socket;
        cmdBox = new SyncBox<>();
        putBox = new SyncBox<>();
        getBox = new SyncBox<>();
    }

    @Override
    public String put(String s) {
        try {
            cmdBox.put("put");
            putBox.put(s);
            return s;
        } catch (InterruptedException ex) {
            Logger.getLogger(EchoClientHandlerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void clear() {
        try {
            cmdBox.put("clear");
        } catch (InterruptedException ex) {
            Logger.getLogger(EchoClientHandlerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String get() {
        try {
            cmdBox.put("get");
            return getBox.get();
        } catch (InterruptedException ex) {
            Logger.getLogger(EchoClientHandlerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void close() throws IOException {
        try {
            cmdBox.put("close");
        } catch (InterruptedException ex) {
            Logger.getLogger(EchoClientHandlerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        try {
            DataInput in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutput out = new DataOutputStream(socket.getOutputStream());
            String cmd = cmdBox.get();
            while (!"close".equalsIgnoreCase(cmd)) {
                if ("put".equalsIgnoreCase(cmd)) {
                    out.writeUTF("put");
                    out.writeUTF(putBox.get());
                } else if ("clear".equalsIgnoreCase(cmd)) {
                    out.writeUTF("clear");
                } else if ("get".equalsIgnoreCase(cmd)) {
                    out.writeUTF("get");
                    getBox.put(in.readUTF());
                } else {
                    throw new RuntimeException("Unknown protocol command: " + cmd);
                }
                cmd = cmdBox.get();
            }
            out.writeUTF("close");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(EchoClientHandlerImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                socket.close();
            } catch (IOException ex) {
                //Nothing we can do here...
            }
        }
    }

}
