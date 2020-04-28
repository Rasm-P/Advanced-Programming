package together;

public abstract class AbstractEvent implements Event
{
    @Override
    public Event1Impl asEvent1()
    {
        throw new UnsupportedOperationException("This is not an event1");
    }

    @Override
    public Event2Impl asEvent2()
    {
        throw new UnsupportedOperationException("This is not an event1");
    }

    @Override
    public Event3Impl asEvent3()
    {
        throw new UnsupportedOperationException("This is not an event1");
    }
}
