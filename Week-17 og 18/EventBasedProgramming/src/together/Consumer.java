package together;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable
{
    private final BlockingQueue<Event> queue;
    private final List<Event1Listener> list1;
    private final List<Event2Listener> list2;
    private final List<Event3Listener> list3;

    public Consumer(BlockingQueue<Event> queue)
    {
        this.queue = queue;
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
    }

    public void addEvent1Listener(Event1Listener listener)
    {
        list1.add(listener);
    }

    public boolean removeEvent1Listener(Event1Listener listener)
    {
        return list1.remove(listener);
    }

    public void addEvent2Listener(Event2Listener listener)
    {
        list2.add(listener);
    }

    public boolean removeEvent2Listener(Event2Listener listener)
    {
        return list2.remove(listener);
    }

    public void addEvent3Listener(Event3Listener listener)
    {
        list3.add(listener);
    }

    public boolean removeEvent1Listener(Event3Listener listener)
    {
        return list3.remove(listener);
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                Event e = queue.take();
                switch(e.getType())
                {
                    case EVENT_1:
                        list1.stream().forEach(l -> l.onEvent(e.asEvent1()));
                        break;
                    case EVENT_2:
                        list2.stream().forEach(l -> l.onEvent(e.asEvent2()));
                        break;
                    case EVENT_3:
                        list3.stream().forEach(l -> l.onEvent(e.asEvent3()));
                        break;
                    default:
                        throw new UnsupportedOperationException("Type: " + e.getType()+ " not implemented!");
                }
            }
        }
        catch(InterruptedException e)
        {
            System.out.println("Interrupted!!!");
        }
    }
}
