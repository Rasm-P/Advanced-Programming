package blocks.basic.impl;

import blocks.basic.BufferBlock;

/**
 * @author Tobias Grundtvig
 */
public class SingleBufferBlockImpl<E> implements BufferBlock<E>
{
    private E obj = null;

    @Override
    public synchronized E get() throws InterruptedException
    {
        while (obj == null)
        {
            wait();
        }
        E res = obj;
        obj = null;
        notifyAll();
        return res;
    }

    @Override
    public synchronized void put(E obj) throws InterruptedException
    {
        while (this.obj != null)
        {
            wait();
        }
        this.obj = obj;
        notifyAll();
    }

    @Override
    public int getCapacity()
    {
        return 1;
    }

    @Override
    public int getLoad()
    {
        return obj == null ? 0 : 1;
    }
}
