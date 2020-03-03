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
public class syncBufferImpl2<E> implements syncBuffer<E> {

    private final List<E> li = new ArrayList();
    private final int capacity;

    public syncBufferImpl2(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public synchronized void put(E item) throws InterruptedException {
        while (li.size() == capacity) {
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
        E item = li.get(0);
        li.remove(li.get(0));
        return item;
    }
}
