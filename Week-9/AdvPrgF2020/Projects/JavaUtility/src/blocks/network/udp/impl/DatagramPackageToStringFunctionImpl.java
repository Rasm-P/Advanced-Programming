package blocks.network.udp.impl;

import java.net.DatagramPacket;
import java.util.function.Function;

public class DatagramPackageToStringFunctionImpl implements Function<DatagramPacket, String>
{
    @Override
    public String apply(DatagramPacket packet)
    {
        return new String(packet.getData(), packet.getOffset(), packet.getLength());
    }
}
