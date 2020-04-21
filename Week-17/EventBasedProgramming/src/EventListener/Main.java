/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import javafx.concurrent.Task;

/**
 *
 * @author rasmu
 */
public class Main {

    public static void main(String[] args) {
        Scanner SCANNER = new Scanner(System.in);
        BlockingQueue<Event> queue = new ArrayBlockingQueue<>(5);
        
        List<EventListener> listeners = new ArrayList<>();
        EventListener ev1 = new EventListener("Listener 1",CommandType.LETTERS);
        listeners.add(ev1);
        EventListener ev2 = new EventListener("Listene 2r",CommandType.LETTERS);
        listeners.add(ev2);
        EventListener ev3 = new EventListener("Listener 3",CommandType.NUMBERS);
        listeners.add(ev3);
        EventListener ev4 = new EventListener("Listener 4",CommandType.STANDARD);
        listeners.add(ev4);
        EventListener ev5 = new EventListener("Listener 5",CommandType.STANDARD);
        listeners.add(ev5);
        InitialEvent initial = new InitialEvent(queue,listeners);
        
        boolean found;
        while (true) {
            found = false;
            String[] line = SCANNER.nextLine().split(" ");
            for (CommandType command : CommandType.values()) {
                if (command.name().equalsIgnoreCase(line[0])) {
                    found = true;
                    Event event = new Event(command,line);
                    queue.offer(event);
                    
                    initial.CallEventListeners(command);
                }
            }
            if (found == false) {
                System.out.println("No command was found!");
            }
        }
    }

}
