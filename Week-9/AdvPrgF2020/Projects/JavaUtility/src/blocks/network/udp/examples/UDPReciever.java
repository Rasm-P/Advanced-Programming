package blocks.network.udp.examples;

import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPReciever
{
    public static void main(String[] args) throws SocketException, UnknownHostException
    {
        UDPToConsole thread = new UDPToConsole(3337, 1024);
        thread.start();
    }
}
