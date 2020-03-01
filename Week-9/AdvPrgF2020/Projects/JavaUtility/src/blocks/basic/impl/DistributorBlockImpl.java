package blocks.basic.impl;

import blocks.basic.DistributorBlock;
import blocks.basic.Output;

import java.util.ArrayList;
import java.util.Collection;

class DistributorBlockImpl<E> implements DistributorBlock<E>
{
    private final Collection<Output<E>> outputs;

    public DistributorBlockImpl()
    {
        this.outputs = new ArrayList<>();
    }

    @Override
    public synchronized void addOutput(Output<E> output)
    {
        outputs.add(output);
    }

    @Override
    public synchronized boolean removeOutput(Output<E> output)
    {
        return outputs.remove(output);
    }

    @Override
    public synchronized Collection<Output<E>> getOutputs()
    {
        //defensive copy
        return new ArrayList<>(outputs);
    }

    @Override
    public synchronized void put(E item) throws InterruptedException
    {
        for(Output<E> output : outputs)
        {
            output.put(item);
        }
    }
}
