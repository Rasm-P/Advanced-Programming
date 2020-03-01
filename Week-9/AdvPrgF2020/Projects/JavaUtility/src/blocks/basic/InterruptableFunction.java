package blocks.basic;

public interface InterruptableFunction<From, To>
{
    public To apply(From obj) throws InterruptedException;
}
