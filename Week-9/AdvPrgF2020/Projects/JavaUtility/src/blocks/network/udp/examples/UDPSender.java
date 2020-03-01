package blocks.network.udp.examples;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPSender
{
    public static void main(String[] args) throws UnknownHostException, SocketException
    {
        KeyboardToUDP sender = new KeyboardToUDP(3333, InetAddress.getByName("192.168.43.42"), 3337);
        sender.start();
    }
}
