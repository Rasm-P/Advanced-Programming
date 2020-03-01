package blocks.basic;

public interface Output<E>
{
    public void put(E item) throws InterruptedException;
}
