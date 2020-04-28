/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventListener;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author rasmu
 */
public class InitialEvent extends Thread {

    private final BlockingQueue<EventInterface> queue;
    private final List<EventListenerInterface> listeners;

    public InitialEvent(BlockingQueue<EventInterface> queue, List<EventListenerInterface> listeners) {
        this.queue = queue;
        this.listeners = listeners;
    }

    @Override
    public void run() {
        while (true) {
            EventInterface e = queue.poll();
            if (e != null) {
                listeners.stream().filter((eventLs) -> (e.getType().equals(eventLs.getType()))).forEachOrdered((eventLs) -> {
                    eventLs.callListener(e.getParam());
                });
            }
        }
    }
    
    public void addEventListener(EventListener eventListener) {
        listeners.add(eventListener);
    }

    public void removeEventListener(EventListener eventListener) {
        listeners.remove(eventListener);
    }

}
