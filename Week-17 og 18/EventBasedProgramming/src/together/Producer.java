package together;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable
{
    private BlockingQueue<Event> queue;
    private ITextIO textIO;

    public Producer(BlockingQueue<Event> queue)
    {
        this.queue = queue;
        textIO = new ITextIO();
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                textIO.put("\nType event and parameters: ");
                String line = textIO.get();
                String[] words = line.split(" ");
                if (words.length > 0)
                {
                    String event = words[0].toLowerCase();
                    if ("event1".equals(event))
                    {
                        Event e = new Event1Impl();
                        queue.put(e);
                        textIO.put("Event created!\n");
                    } else if ("event2".equals(event))
                    {
                        if (words.length == 2)
                        {
                            Event e = new Event2Impl(words[1]);
                            queue.put(e);
                            textIO.put("Event created!\n");
                        } else
                        {
                            textIO.put("\nEvent2 must have exactly one parameter!\n");
                        }
                    } else if ("event3".equals(event))
                    {
                        if (words.length == 3)
                        {
                            Event e = new Event3Impl(words[1], words[2]);
                            queue.put(e);
                            textIO.put("Event created!\n");
                        } else
                        {
                            textIO.put("\nEvent3 must have exactly two parameters!\n");
                        }
                    } else
                    {
                        textIO.put(words[0] + " is not a valid event type!");
                    }
                } else
                {
                    textIO.put("\nYou must type something!\n");
                }
            }
        }
        catch(InterruptedException e)
        {
            System.out.println("Interrupted!!!");
        }
    }
}
