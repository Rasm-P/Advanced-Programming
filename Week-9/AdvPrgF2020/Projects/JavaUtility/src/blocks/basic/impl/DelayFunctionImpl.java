package blocks.basic.impl;

import blocks.basic.InterruptableFunction;

import java.util.Random;

public class DelayFunctionImpl<E> implements InterruptableFunction<E, E>
{
    private final Random rnd = new Random();
    private final int minTime;
    private final int maxTime;

    public DelayFunctionImpl(int minTime, int maxTime)
    {
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    @Override
    public E apply(E item) throws InterruptedException
    {
        wait(rnd.nextInt(maxTime - minTime) + minTime);
        return item;
    }
}
