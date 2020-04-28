package together;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Example1
{
    public static void main(String[] args)
    {
        BlockingQueue<Event> queue = new ArrayBlockingQueue<Event>(100);
        Producer p = new Producer(queue);
        Thread pt = new Thread(p);
        Consumer c = new Consumer(queue);
        c.addEvent1Listener(e -> System.out.println("\nEvent1 dispatched!"));
        c.addEvent1Listener(e -> System.out.println("\nThis is another Event1 listener!"));
        c.addEvent2Listener(e -> System.out.println("\nEvent2(" + e.getParam() + ") dispatched!"));
        c.addEvent3Listener(e -> System.out.println("\nEvent3(" + e.getParam1() + ", " + e.getParam2() + ") dispatched!"));
        Thread ct = new Thread(c);
        pt.start();
        ct.start();
        System.out.println("Done!");
    }
}
