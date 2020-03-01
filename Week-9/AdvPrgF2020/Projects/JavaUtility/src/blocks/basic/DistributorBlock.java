package blocks.basic;

import java.util.Collection;

public interface DistributorBlock<E> extends Output<E>
{
    public void addOutput(Output<E> output);
    public boolean removeOutput(Output<E> output);
    public Collection<Output<E>> getOutputs();
}
