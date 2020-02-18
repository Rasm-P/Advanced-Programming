/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textio;


import sync.SyncBox;

import java.io.BufferedInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tobias Grundtvig
 */
public class TextIOServer implements Runnable
{
    private final int port;
    private final ITextIOConnectionHandler handler;
    private ServerSocket serverSocket;
    private boolean stop;

    public TextIOServer(int port, ITextIOConnectionHandler handler)
    {
        this.port = port;
        this.handler = handler;
    }
    
    @Override
    public void run()
    {
        try
        {
            String adr = java.net.InetAddress.getLocalHost().getHostAddress();
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on " + adr + ":" + port);
            stop = false;
            while (!stop)
            {
                try
                {
                    //Throws a SocketException when closed();
                    Socket socket = serverSocket.accept();
                    TextIOServerSocket textSocket = new TextIOServerSocket(socket);
                    new Thread(textSocket).start();
                    handler.handleConnection(textSocket);
                } catch (SocketException e)
                {
                    // Nothing to do, this is actually not an error, 
                    // but the only way we can break out of the accept method.
                }                
            }
        } catch (IOException e)
        {
            System.out.println("Server crashed!");
            throw new RuntimeException(e);
        }
        System.out.println("Server stopped!");
    }
    
    
    private class TextIOServerSocket implements ITextIO, Runnable
    {

        private final Socket socket;
        private final SyncBox<String> cmdBox;
        private final SyncBox<String> putBox;
        private final SyncBox<String> getBox;

        public TextIOServerSocket(Socket socket)
        {
            this.socket = socket;
            cmdBox = new SyncBox<>();
            putBox = new SyncBox<>();
            getBox = new SyncBox<>();
        }

        @Override
        public void put(String s)
        {
            try {
                cmdBox.put("put");
                putBox.put(s);
            } catch (InterruptedException ex) {
                Logger.getLogger(TextIOServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public void clear()
        {
            try {
                cmdBox.put("clear");
            } catch (InterruptedException ex) {
                Logger.getLogger(TextIOServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public String get()
        {
            try {
                cmdBox.put("get");
                return getBox.get();
            } catch (InterruptedException ex) {
                Logger.getLogger(TextIOServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }

        @Override
        public void close() throws IOException
        {
            try {
                cmdBox.put("close");
            } catch (InterruptedException ex) {
                Logger.getLogger(TextIOServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public void run()
        {
            try
            {
                DataInput in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                DataOutput out = new DataOutputStream(socket.getOutputStream());
                String cmd = cmdBox.get();
                while (!"close".equalsIgnoreCase(cmd))
                {
                    if ("put".equalsIgnoreCase(cmd))
                    {
                        out.writeUTF("put");
                        out.writeUTF(putBox.get());
                    } else if ("clear".equalsIgnoreCase(cmd))
                    {
                        out.writeUTF("clear");
                    } else if ("get".equalsIgnoreCase(cmd))
                    {
                        out.writeUTF("get");
                        getBox.put(in.readUTF());
                    } else
                    {
                        throw new RuntimeException("Unknown protocol command: " + cmd);
                    }
                    cmd = cmdBox.get();
                }
                out.writeUTF("close");
            } catch (IOException ex)
            {
                throw new RuntimeException(ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(TextIOServer.class.getName()).log(Level.SEVERE, null, ex);
            } finally
            {
                try
                {
                    socket.close();
                } catch (IOException ex)
                {
                    //Nothing we can do here...
                }
            }
        }

    }
}
