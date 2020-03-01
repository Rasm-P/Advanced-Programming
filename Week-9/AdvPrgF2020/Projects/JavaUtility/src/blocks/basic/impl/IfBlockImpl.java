package blocks.basic.impl;

import blocks.basic.IfBlock;
import blocks.basic.Output;

import java.util.function.Predicate;

public class IfBlockImpl<E> implements IfBlock<E>
{
    private Predicate<E> predicate;
    private Output<E> trueOut;
    private Output<E> falseOut;

    public IfBlockImpl(Predicate<E> predicate)
    {
        this.predicate = predicate;
    }

    @Override
    public void setTrueOutput(Output<E> output)
    {
        this.trueOut = output;
    }

    @Override
    public void setFalseOutput(Output<E> output)
    {
        this.falseOut = output;
    }

    @Override
    public void put(E item) throws InterruptedException
    {
        if(trueOut == null || falseOut == null)
        {
            throw new RuntimeException("Block not fully connected!");
        }
        if(predicate.test(item))
        {
            trueOut.put(item);
        }
        else
        {
            falseOut.put(item);
        }
    }
}
