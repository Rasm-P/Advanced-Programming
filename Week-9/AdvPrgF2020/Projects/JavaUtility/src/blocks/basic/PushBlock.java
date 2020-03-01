package blocks.basic;

public interface PushBlock<From, To> extends Output<From>
{
    public void setOutput(Output<To> output);
}
