package blocks.network.udp.impl;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.function.Function;

class AddressBlockImpl implements Function<DatagramPacket, DatagramPacket>
{
    private final InetAddress iaddr;
    private final int port;

    public AddressBlockImpl(InetAddress iaddr, int port)
    {
        this.iaddr = iaddr;
        this.port = port;
    }

    @Override
    public DatagramPacket apply(DatagramPacket packet)
    {
        packet.setAddress(iaddr);
        packet.setPort(port);
        return packet;
    }
}
