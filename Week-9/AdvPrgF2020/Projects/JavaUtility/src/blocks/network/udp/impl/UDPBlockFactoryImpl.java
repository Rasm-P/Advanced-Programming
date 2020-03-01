package blocks.network.udp.impl;

import blocks.errorlogging.impl.ErrorLogImpl;
import blocks.network.SocketBlock;
import blocks.network.udp.UDPBlockFactory;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.function.Function;

public class UDPBlockFactoryImpl implements UDPBlockFactory
{
    private static UDPBlockFactory instance = null;

    private final Function<String, DatagramPacket> strToDatagram = new StringToDatagramPacketFunctionImpl();
    private final Function<DatagramPacket, String> datagramToString = new DatagramPackageToStringFunctionImpl();


    public static UDPBlockFactory getInstance()
    {
        if(instance == null)
        {
            instance = new UDPBlockFactoryImpl();
        }
        return instance;
    }

    private UDPBlockFactoryImpl() {}

    @Override
    public SocketBlock<DatagramPacket> getUDPSocketBlock(int port, int maxPacketSize) throws SocketException, UnknownHostException
    {
        return new UDPSocketBlockImpl(ErrorLogImpl.getInstance(), port, maxPacketSize);
    }

    @Override
    public Function<DatagramPacket, DatagramPacket> getAddressFunction(InetAddress address, int port)
    {
        return new AddressBlockImpl(address, port);
    }

    @Override
    public Function<String, DatagramPacket> getStringToDatagramPacketFunction()
    {
        return strToDatagram;
    }

    @Override
    public Function<DatagramPacket, String> getDatagramPacketToStringFunction()
    {
        return datagramToString;
    }
}
