package blocks.basic;

public interface Input<E>
{
    public E get() throws InterruptedException;
}
