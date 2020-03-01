package blocks.basic.impl;

import blocks.basic.*;

import java.util.function.Function;
import java.util.function.Predicate;
//Singleton factory
public class BasicBlockFactoryImpl implements BasicBlockFactory
{
    private static BasicBlockFactory instance = null;

    public static BasicBlockFactory getInstance()
    {
        if(instance == null)
        {
            instance = new BasicBlockFactoryImpl();
        }
        return instance;
    }

    private BasicBlockFactoryImpl() {}

    @Override
    public <E> IfBlock<E> getIfBlock(Predicate<E> predicate)
    {
        return new IfBlockImpl<>(predicate);
    }

    @Override
    public <E> IfBlock<E> getInterruptableIfBlock(InterruptablePredicate<E> predicate)
    {
        return new InterruptableIfBlockImpl<>(predicate);
    }

    @Override
    public <E> BufferBlock<E> getBufferBlock(int capacity)
    {
        if(capacity < 1)
        {
            throw new IllegalArgumentException("Capacity must be at least 1");
        }
        if(capacity == 1)
        {
            return new SingleBufferBlockImpl<>();
        }
        return new BufferBlockImpl<E>(capacity);
    }

    @Override
    public <E> ThreadBlock<E> getThreadBlock()
    {
        return new ThreadBlockImpl<>();
    }

    @Override
    public <E> DistributorBlock<E> getDistributorBlock()
    {
        return new DistributorBlockImpl<>();
    }

    @Override
    public <From, To> PullBlock<From, To> getInterruptablePullBlock(InterruptableFunction<From, To> fun)
    {
        return new InterruptablePullBlockImpl<>(fun);
    }

    @Override
    public <From, To> PushBlock<From, To> getInterruptablePushBlock(InterruptableFunction<From, To> fun)
    {
        return new InterruptablePushBlockImpl<>(fun);
    }

    @Override
    public <From, To> PullBlock<From, To> getPullBlock(Function<From, To> fun)
    {
        return new PullBlockImpl<>(fun);
    }

    @Override
    public <From, To> PushBlock<From, To> getPushBlock(Function<From, To> fun)
    {
        return new PushBlockImpl<>(fun);
    }

    @Override
    public <E> Output<E> getTrashcanBlock()
    {
        return new Output<E>()
        {
            @Override
            public void put(E item) throws InterruptedException {}
        };
    }

    @Override
    public Input<String> getKeyboardBlock()
    {
        return KeyboardBlockImpl.getInstance();
    }

    @Override
    public Output<String> getConsoleBlock()
    {
        return ConsoleBlockImpl.getInstance();
    }

    @Override
    public <E> InterruptableFunction<E, E> getDelayFunction(int minDelay, int maxDelay)
    {
        return new DelayFunctionImpl<>(minDelay, maxDelay);
    }
}
