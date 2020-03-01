package blocks.basic.impl;

import blocks.basic.BufferBlock;

class BufferBlockImpl<E> implements BufferBlock<E>
{
    private final E[] array;
    private int size;
    private int first;

    @SuppressWarnings("unchecked")
    BufferBlockImpl(int size)
    {
        array = (E[]) new Object[size];
        this.size = 0;
        this.first = 0;
    }



    @Override
    public synchronized E get() throws InterruptedException
    {
        while (size == 0)
        {
            wait();
        }
        return doGet();
    }

    @Override
    public synchronized void put(E item) throws InterruptedException
    {
        while (size == array.length)
        {
            wait();
        }
        doPut(item);
    }

    private synchronized E doGet()
    {
        E res = array[first];
        array[first] = null;
        first = (first + 1) % array.length;
        --size;
        notifyAll();
        return res;
    }

    private synchronized void doPut(E item)
    {
        int index = (first + size) % array.length;
        array[index] = item;
        ++size;
        notifyAll();
    }

    @Override
    public synchronized int getCapacity()
    {
        return array.length;
    }

    @Override
    public synchronized int getLoad()
    {
        return size;
    }
}
