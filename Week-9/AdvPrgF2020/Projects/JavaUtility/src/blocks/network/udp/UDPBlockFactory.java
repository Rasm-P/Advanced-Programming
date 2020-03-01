package blocks.network.udp;

import blocks.errorlogging.ErrorLog;
import blocks.network.SocketBlock;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.function.Function;

public interface UDPBlockFactory
{
    //UDPBlocks
    public SocketBlock<DatagramPacket> getUDPSocketBlock(int port, int maxPacketSize) throws SocketException, UnknownHostException;
    //UDPFunctions
    public Function<DatagramPacket, DatagramPacket> getAddressFunction(InetAddress address, int port);
    public Function<String, DatagramPacket> getStringToDatagramPacketFunction();
    public Function<DatagramPacket, String> getDatagramPacketToStringFunction();
}
