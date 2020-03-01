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

public class SimpleUDPExample
{
    public static void main(String[] args) throws UnknownHostException, SocketException
    {
        BasicBlockFactory basicFactory = BasicBlockFactoryImpl.getInstance();
        UDPBlockFactory udpFactory = UDPBlockFactoryImpl.getInstance();
        Input<String> keyboard = basicFactory.getKeyboardBlock();
        Output<String> console = basicFactory.getConsoleBlock();
        ThreadBlock<DatagramPacket> writeThread = basicFactory.getThreadBlock();
        ThreadBlock<DatagramPacket> readThread = basicFactory.getThreadBlock();
        PullBlock<String, DatagramPacket> stringToDatagramPacket = basicFactory.getPullBlock(udpFactory.getStringToDatagramPacketFunction());
        PushBlock<DatagramPacket, String> datagramPacketToString = basicFactory.getPushBlock(udpFactory.getDatagramPacketToStringFunction());
        PushBlock<DatagramPacket, DatagramPacket> addressBlock = basicFactory.getPushBlock(udpFactory.getAddressFunction(InetAddress.getLocalHost(), 3337));
        SocketBlock<DatagramPacket> socket = udpFactory.getUDPSocketBlock(3337, 1024);

        System.out.println("Socket on: " + socket.getAddress());

        //Hook it up

        //Writer
        stringToDatagramPacket.setInput(keyboard);
        writeThread.setInput(stringToDatagramPacket);
        writeThread.setOutput(addressBlock);
        addressBlock.setOutput(socket);

        //Reader
        readThread.setInput(socket);
        readThread.setOutput(datagramPacketToString);
        datagramPacketToString.setOutput(console);


        //Start the thread
        readThread.start();
        writeThread.start();
    }
}
