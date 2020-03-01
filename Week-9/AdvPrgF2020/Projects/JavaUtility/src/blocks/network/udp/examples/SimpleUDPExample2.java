package blocks.network.udp.examples;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SimpleUDPExample2
{
    public static void main(String[] args) throws UnknownHostException, SocketException
    {
        KeyboardToUDP sender = new KeyboardToUDP(3333, InetAddress.getLocalHost(), 3337);
        UDPToConsole receiver = new UDPToConsole(3337, 1024);
        receiver.start();
        sender.start();
    }
}
