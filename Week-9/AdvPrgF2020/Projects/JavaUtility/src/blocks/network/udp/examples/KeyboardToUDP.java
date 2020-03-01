package blocks.network.udp.examples;

import blocks.basic.*;
import blocks.basic.impl.BasicBlockFactoryImpl;
import blocks.network.SocketBlock;
import blocks.network.udp.UDPBlockFactory;
import blocks.network.udp.impl.UDPBlockFactoryImpl;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class KeyboardToUDP implements ThreadControl
{
    private ThreadControl threadCtrl;

    public KeyboardToUDP(int socketPort, InetAddress receiverAddress, int receiverPort) throws SocketException, UnknownHostException
    {
        //Get factories
        BasicBlockFactory basicFactory = BasicBlockFactoryImpl.getInstance();
        UDPBlockFactory udpFactory = UDPBlockFactoryImpl.getInstance();

        //Create blocks
        Input<String> keyboard = basicFactory.getKeyboardBlock();
        PullBlock<String, DatagramPacket> stringToDatagramPacket = basicFactory.getPullBlock(udpFactory.getStringToDatagramPacketFunction());
        PullBlock<DatagramPacket, DatagramPacket> addressBlock = basicFactory.getPullBlock(udpFactory.getAddressFunction(receiverAddress, receiverPort));
        ThreadBlock<DatagramPacket> thread = basicFactory.getThreadBlock();
        //Setting maxPacketsize to 0 since we will not read from the socket...
        SocketBlock<DatagramPacket> socket = udpFactory.getUDPSocketBlock(socketPort, 0);

        //Hook up blocks
        stringToDatagramPacket.setInput(keyboard);
        addressBlock.setInput(stringToDatagramPacket);
        thread.setInput(addressBlock);
        thread.setOutput(socket);

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
