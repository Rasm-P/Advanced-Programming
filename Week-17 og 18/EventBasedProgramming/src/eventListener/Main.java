/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventListener;

import static eventListener.Populate.populateList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author rasmu
 */
public class Main {

    public static void main(String[] args) {
        Scanner SCANNER = new Scanner(System.in);
        BlockingQueue<EventInterface> queue = new ArrayBlockingQueue<>(100);

        List<EventListenerInterface> listeners = populateList();
        InitialEvent initial = new InitialEvent(queue, listeners);
        initial.start();

        boolean found;
        while (true) {
            found = false;
            String[] line = SCANNER.nextLine().split(" ");
            for (CommandType command : CommandType.values()) {
                if (command.name().equalsIgnoreCase(line[0])) {
                    found = true;
                    Event event = new Event(command, line);
                    queue.offer(event);
                    //initial.CallEventListeners(command);
                }
            }
            if (found == false) {
                System.out.println("No command was found!");
            }
        }
    }

}
