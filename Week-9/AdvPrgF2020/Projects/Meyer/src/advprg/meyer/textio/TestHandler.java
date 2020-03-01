/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advprg.meyer.textio;

import java.util.ArrayList;

/**
 *
 * @author Tobias Grundtvig
 */
public class TestHandler implements ITextIOConnectionHandler
{
    private final ArrayList<ITextIO> clients;

    public TestHandler()
    {
        clients = new ArrayList<>();
    }
    
    private class Player implements Runnable
    {
        private String name;
        private final ITextIO io;

        public Player(ITextIO io)
        {
            this.io = io;
        }

        @Override
        public void run()
        {
            io.put("Welcome to the test!\n");
            io.put("Please enter your name:\n");
            name = io.get();
            io.put("Hi " + name + " good to see you here.\n");
            doWelcome(this);
        }
    }
    
    @Override
    public synchronized void handleConnection(ITextIO client)
    {
        Player p = new Player(client);
        new Thread(p).start();
    }
    
    private synchronized void doWelcome(Player p)
    {
        for(ITextIO other : clients)
        {
            other.put(p.name + " has entered the test.\n");
        }
        clients.add(p.io);
    }
    
    public static void main(String[] args)
    {
        TextIOServer server = new TextIOServer(3337, new TestHandler());
        Thread serverThread = new Thread(server);
        serverThread.start();
        
        for(int i = 0; i < 2; ++i)
        {
            ITextIO gui = GUITextIO.createGUI();
            gui.put("Starting test\n");
            TextIOClientSocket client = new TextIOClientSocket("localhost", 3337, gui);
            Thread clientThread = new Thread(client);
            clientThread.start();
        }
        
        System.out.println("Main thread done!");
        
    }
}
