package blocks.basic.impl;

import blocks.basic.IfBlock;
import blocks.basic.InterruptablePredicate;
import blocks.basic.Output;


public class InterruptableIfBlockImpl<E> implements IfBlock<E>
{
    private InterruptablePredicate<E> predicate;
    private Output<E> trueOut;
    private Output<E> falseOut;

    public InterruptableIfBlockImpl(InterruptablePredicate<E> predicate)
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
