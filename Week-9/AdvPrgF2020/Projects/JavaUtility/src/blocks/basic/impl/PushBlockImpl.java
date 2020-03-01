package blocks.basic.impl;

import blocks.basic.Output;
import blocks.basic.PushBlock;

import java.util.function.Function;

class PushBlockImpl<From, To> implements PushBlock<From, To>
{
    private final Function<From, To> fun;
    private Output<To> output;

    public PushBlockImpl(Function<From, To> fun)
    {
        this.fun = fun;
    }

    @Override
    public void setOutput(Output<To> output)
    {
        this.output = output;
    }

    @Override
    public void put(From item) throws InterruptedException
    {
        if(output == null)
        {
            throw new IllegalStateException("PushBlock's output is null!");
        }
        output.put(fun.apply(item));
    }
}
