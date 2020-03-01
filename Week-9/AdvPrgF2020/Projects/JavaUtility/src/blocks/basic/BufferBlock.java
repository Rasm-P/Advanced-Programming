package blocks.basic;

public interface BufferBlock<E> extends Output<E>, Input<E>
{
    public int getCapacity();
    public int getLoad();
}
