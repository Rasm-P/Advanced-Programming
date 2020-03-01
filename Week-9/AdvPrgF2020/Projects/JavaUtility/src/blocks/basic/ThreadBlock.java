package blocks.basic;

public interface ThreadBlock<E> extends ThreadControl
{
    public void setInput(Input<E> input) throws IllegalStateException;
    public void setOutput(Output<E> output) throws IllegalStateException;
}
