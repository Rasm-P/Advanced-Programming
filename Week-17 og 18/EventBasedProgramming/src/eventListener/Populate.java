/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventListener;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rasmu
 */
public class Populate {

    public static List<EventListenerInterface> populateList() {
        List<EventListenerInterface> listeners = new ArrayList<>();
        EventListener ev1 = new EventListener("Listener 1", CommandType.LETTERS);
        listeners.add(ev1);
        EventListener ev2 = new EventListener("Listene 2r", CommandType.LETTERS);
        listeners.add(ev2);
        EventListener ev3 = new EventListener("Listener 3", CommandType.NUMBERS);
        listeners.add(ev3);
        EventListener ev4 = new EventListener("Listener 4", CommandType.STANDARD);
        listeners.add(ev4);
        EventListener ev5 = new EventListener("Listener 5", CommandType.STANDARD);
        listeners.add(ev5);
        return listeners;
    }

}
