package blocks.basic.impl;

import blocks.basic.Input;
import blocks.basic.Output;
import blocks.basic.ThreadBlock;

class ThreadBlockImpl<E> implements ThreadBlock<E>, Runnable
{
    private Input<E> input;
    private Output<E> output;
    private Thread workerThread;
    private volatile boolean running;
    private volatile boolean doingOutput;


    public ThreadBlockImpl()
    {
        this.input = null;
        this.output = null;
        this.running = false;
    }

    @Override
    public void run()
    {
        while (running)
        {
            try
            {
                E item = input.get();
                doingOutput = true;
                output.put(item);
                doingOutput = false;
                synchronized (this)
                {
                    this.notifyAll();
                }
            } catch (InterruptedException e)
            {
                //Do nothing, let 'stopped' do its work...
            }
        }
    }

    @Override
    public synchronized void start() throws IllegalStateException
    {
        if(running) return;
        if(input == null || output == null)
        {
            throw new IllegalStateException("Worker may only be started if both input and output are connected!");
        }
        running = true;
        workerThread = new Thread(this);
        workerThread.start();
    }

    @Override
    public synchronized void stopGracefully() throws InterruptedException
    {
        if(!running) return;
        while (doingOutput)
        {
            wait();
        }
        stopNow();
    }

    @Override
    public synchronized void stopNow() throws InterruptedException
    {
        if(!running) return;
        running = false;
        workerThread.interrupt();
        workerThread.join();
    }

    @Override
    public synchronized boolean isRunning()
    {
        return running;
    }

    @Override
    public synchronized void setInput(Input<E> input) throws IllegalStateException
    {
        if(running)
        {
            throw new IllegalStateException("Input can only be set, when worker is not running!");
        }
        this.input = input;
        this.notifyAll();
    }

    @Override
    public synchronized void setOutput(Output<E> output) throws IllegalStateException
    {
        if(running)
        {
            throw new IllegalStateException("Output can only be set, when worker is not running!");
        }
        this.output = output;
        notifyAll();
    }
}

