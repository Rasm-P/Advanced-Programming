package blocks.basic;

import blocks.errorlogging.ErrorLog;

import java.util.function.Function;
import java.util.function.Predicate;

public interface BasicBlockFactory
{
    //General blocks
    public <E> IfBlock<E> getIfBlock(Predicate<E> predicate);
    public <E> IfBlock<E> getInterruptableIfBlock(InterruptablePredicate<E> predicate);
    public <E> BufferBlock<E> getBufferBlock(int capacity);
    public <E> ThreadBlock<E> getThreadBlock();
    public <E> DistributorBlock<E> getDistributorBlock();
    public <From, To> PullBlock<From, To> getInterruptablePullBlock(InterruptableFunction<From, To> fun);
    public <From, To> PushBlock<From, To> getInterruptablePushBlock(InterruptableFunction<From, To> fun);
    public <From, To> PullBlock<From, To> getPullBlock(Function<From, To> fun);
    public <From, To> PushBlock<From, To> getPushBlock(Function<From, To> fun);

    //TrashcanBlock
    public <E> Output<E> getTrashcanBlock();

    //String blocks
    public Input<String> getKeyboardBlock();
    public Output<String> getConsoleBlock();

    //Time related functions
    public <E> InterruptableFunction<E, E> getDelayFunction(int minDelay, int maxDelay);
}
