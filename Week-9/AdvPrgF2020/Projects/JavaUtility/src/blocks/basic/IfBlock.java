package blocks.basic;

public interface IfBlock<E> extends Output<E>
{
    public void setTrueOutput(Output<E> output);
    public void setFalseOutput(Output<E> output);
}
