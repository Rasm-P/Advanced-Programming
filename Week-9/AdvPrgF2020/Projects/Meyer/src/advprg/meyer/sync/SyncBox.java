/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advprg.meyer.sync;

/**
 * @author Tobias Grundtvig
 */
public class SyncBox<E>
{
    private E obj = null;

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

    public synchronized void put(E obj) throws InterruptedException
    {
        while (this.obj != null)
        {
            wait();
        }
        this.obj = obj;
        notifyAll();
    }
}
