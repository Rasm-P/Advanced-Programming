/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventListener;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author rasmu
 */
public class InitialEvent {
    
    private final BlockingQueue<Event> queue;
    private final List<EventListener> listeners;

    public InitialEvent(BlockingQueue<Event> queue, List<EventListener> listeners) {
        this.queue = queue;
        this.listeners = listeners;
    }

    public void CallEventListeners(CommandType type) {
        for (Event event : queue) {
            for (EventListener eventLs : listeners) {
                if (event.getType().equals(eventLs.getType())) {
                    eventLs.callListener();
                }
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
