package blocks.network.udp.impl;

import blocks.errorlogging.ErrorLog;
import blocks.network.SocketBlock;

import java.io.IOException;
import java.net.*;

public class UDPSocketBlockImpl implements SocketBlock<DatagramPacket>
{
    private final ErrorLog err;
    private final DatagramSocket socket;
    private final int maxPacketSize;
    private final InetAddress address;

    public UDPSocketBlockImpl(ErrorLog err, int port, int maxPacketSize) throws SocketException, UnknownHostException
    {
        this.err = err;
        this.socket = new DatagramSocket(port);
        this.maxPacketSize = maxPacketSize;
        this.address = InetAddress.getLocalHost();
    }

    @Override
    public int getPort()
    {
        return socket.getPort();
    }

    @Override
    public InetAddress getAddress()
    {
        return address;
    }

    @Override
    public DatagramPacket get() throws InterruptedException
    {
        while(true)
        {
            try
            {
                byte[] buffer = new byte[maxPacketSize];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                return packet;
            } catch (IOException e)
            {
                err.reportIOException(e);
                Thread.sleep(5000);
            }
        }
    }

    @Override
    public void put(DatagramPacket item) throws InterruptedException
    {
        while(true)
        {
            try
            {
                socket.send(item);
                return;
            } catch (IOException e)
            {
                err.reportIOException(e);
                Thread.sleep(5000);
            }
        }
    }
}
