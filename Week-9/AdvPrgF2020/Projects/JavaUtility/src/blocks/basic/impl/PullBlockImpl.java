package blocks.basic.impl;

import blocks.basic.Input;
import blocks.basic.PullBlock;

import java.util.function.Function;

class PullBlockImpl<From, To> implements PullBlock<From, To>
{
    private final Function<From, To> fun;
    private Input<From> input;

    public PullBlockImpl(Function<From, To> fun)
    {
        this.fun = fun;
    }

    @Override
    public void setInput(Input<From> input)
    {
        this.input = input;
    }

    @Override
    public To get() throws InterruptedException
    {
        if(input == null)
        {
            throw new IllegalStateException("PullBlock's input is null!");
        }
        return fun.apply(input.get());
    }
}
