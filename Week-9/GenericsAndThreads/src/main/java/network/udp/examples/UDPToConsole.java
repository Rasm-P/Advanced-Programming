package network.udp.examples;

import basic.Output;
import basic.BasicBlockFactory;
import basic.ThreadControl;
import basic.ThreadBlock;
import basic.PushBlock;
import basic.impl.BasicBlockFactoryImpl;
import network.SocketBlock;
import network.udp.UDPBlockFactory;
import network.udp.impl.UDPBlockFactoryImpl;

import java.net.DatagramPacket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPToConsole implements ThreadControl
{
    private ThreadControl threadCtrl;

    public UDPToConsole(int port, int maxPackageSize) throws SocketException, UnknownHostException
    {
        //Get factories
        BasicBlockFactory basicFactory = BasicBlockFactoryImpl.getInstance();
        UDPBlockFactory udpFactory = UDPBlockFactoryImpl.getInstance();

        //Create blocks
        SocketBlock<DatagramPacket> socket = udpFactory.getUDPSocketBlock(port, maxPackageSize);
        ThreadBlock<DatagramPacket> thread = basicFactory.getThreadBlock();
        PushBlock<DatagramPacket, String> datagramPacketToString = basicFactory.getPushBlock(udpFactory.getDatagramPacketToStringFunction());
        Output<String> console = basicFactory.getConsoleBlock();

        //Hook up blocks
        thread.setInput(socket);
        thread.setOutput(datagramPacketToString);
        datagramPacketToString.setOutput(console);

        //Set thread control
        threadCtrl = thread;
    }

    @Override
    public void start()
    {
        threadCtrl.start();
    }

    @Override
    public void stopGracefully() throws InterruptedException
    {
        threadCtrl.stopGracefully();
    }

    @Override
    public void stopNow() throws InterruptedException
    {
        threadCtrl.stopNow();
    }

    @Override
    public boolean isRunning()
    {
        return threadCtrl.isRunning();
    }
}
