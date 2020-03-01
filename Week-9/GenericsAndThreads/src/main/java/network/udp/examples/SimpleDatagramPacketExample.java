package network.udp.examples;

import basic.Input;
import basic.Output;
import basic.BasicBlockFactory;
import basic.PullBlock;
import basic.ThreadBlock;
import basic.PushBlock;
import basic.impl.BasicBlockFactoryImpl;
import network.udp.UDPBlockFactory;
import network.udp.impl.UDPBlockFactoryImpl;

import java.net.DatagramPacket;

public class SimpleDatagramPacketExample
{
    public static void main(String[] args)
    {
        BasicBlockFactory basicFactory = BasicBlockFactoryImpl.getInstance();
        UDPBlockFactory udpFactory = UDPBlockFactoryImpl.getInstance();
        Input<String> keyboard = basicFactory.getKeyboardBlock();
        Output<String> console = basicFactory.getConsoleBlock();
        ThreadBlock thread = basicFactory.getThreadBlock();
        PullBlock<String, DatagramPacket> stringToDatagramPacket = basicFactory.getPullBlock(udpFactory.getStringToDatagramPacketFunction());
        PushBlock<DatagramPacket, String> datagramPacketToString = basicFactory.getPushBlock(udpFactory.getDatagramPacketToStringFunction());

        //Hook it up
        stringToDatagramPacket.setInput(keyboard);
        thread.setInput(stringToDatagramPacket);
        thread.setOutput(datagramPacketToString);
        datagramPacketToString.setOutput(console);

        //Start the thread
        thread.start();
    }
}
