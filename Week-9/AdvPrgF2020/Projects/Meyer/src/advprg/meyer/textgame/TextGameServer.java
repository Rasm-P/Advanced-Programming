/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advprg.meyer.textgame;


import advprg.meyer.sync.SyncBox;
import advprg.meyer.textio.ITextIO;


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
public class TextGameServer implements Runnable
{

    private final ITextGame game;
    private final int port;
    private ServerSocket serverSocket;

    public TextGameServer(ITextGame game, int port)
    {
        this.game = game;
        this.port = port;
    }

    @Override
    public void run()
    {
        try
        {
            String adr = java.net.InetAddress.getLocalHost().getHostAddress();
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on " + adr + ":" + port);
            int count = game.getNumberOfPlayers();
            int index = 0;
            ITextIO[] players = new ITextIO[count];
            while (index < count)
            {
                try
                {
                    //Throws a SocketException when closed();
                    Socket socket = serverSocket.accept();
                    TextIOServerSocket textSocket = new TextIOServerSocket(socket);
                    new Thread(textSocket).start();
                    players[index] = textSocket;
                    int playersLeft = count - index - 1;
                    if (playersLeft > 0)
                    {
                        for (int i = 0; i <= index; ++i)
                        {
                            players[i].put("\nWaiting for " + playersLeft + " player(s) to connect\n");
                        }
                    }
                    ++index;

                } catch (SocketException e)
                {
                    // Nothing to do, this is actually not an error, 
                    // but the only way we can break out of the accept method.
                }
            }
            game.startGame(players);
            //Wait for players to read any final messages...
            try
            {
                Thread.sleep(1000);
            } catch (InterruptedException ex)
            {
                Logger.getLogger(TextGameServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int i = 0; i < count; ++i)
            {
                players[i].close();
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
            cmdBox.put("put");
            putBox.put(s);
        }

        @Override
        public void clear()
        {
            cmdBox.put("clear");
        }

        @Override
        public String get()
        {
            cmdBox.put("get");
            return getBox.get();
        }

        @Override
        public void close() throws IOException
        {
            cmdBox.put("close");
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
