package basic;

public interface Input<E>
{
    public E get() throws InterruptedException;
}
