package blocks.network;

import blocks.basic.Input;
import blocks.basic.Output;

import java.net.InetAddress;

public interface SocketBlock<E> extends Input<E>, Output<E>
{
    public int getPort();
    public InetAddress getAddress();
}
