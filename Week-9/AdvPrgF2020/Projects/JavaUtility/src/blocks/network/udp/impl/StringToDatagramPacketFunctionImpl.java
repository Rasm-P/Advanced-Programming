package blocks.network.udp.impl;

import java.net.DatagramPacket;
import java.util.function.Function;

public class StringToDatagramPacketFunctionImpl implements Function<String, DatagramPacket>
{
    @Override
    public DatagramPacket apply(String str)
    {
        byte[] buf = str.getBytes();
        return new DatagramPacket(buf, buf.length);
    }
}
