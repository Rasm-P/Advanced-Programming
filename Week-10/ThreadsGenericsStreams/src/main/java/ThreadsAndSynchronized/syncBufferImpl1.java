/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThreadsAndSynchronized;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rasmu
 * @param <E>
 */
public class syncBufferImpl1<E> implements syncBuffer<E> {

    private final List<E> li = new ArrayList();
    //E[] arr = (E[]) new Object[0];

    public syncBufferImpl1() {
    }

    @Override
    public synchronized void put(E item) throws InterruptedException {
        while (!li.isEmpty()) {
            wait();
        }
        li.add(item);
        notifyAll();
    }

    @Override
    public synchronized E get() throws InterruptedException {
        while (li.isEmpty()) {
            wait();
        }
        notifyAll();
        return li.get(0);
    }
}
