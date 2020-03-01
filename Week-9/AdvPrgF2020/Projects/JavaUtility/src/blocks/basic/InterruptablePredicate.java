package blocks.basic;

public interface InterruptablePredicate<E>
{
    public boolean test(E obj) throws InterruptedException;
}
