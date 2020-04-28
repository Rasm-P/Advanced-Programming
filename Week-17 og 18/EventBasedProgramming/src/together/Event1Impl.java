package together;

public class Event1Impl extends AbstractEvent
{
    @Override
    public EventType getType()
    {
        return EventType.EVENT_1;
    }

    @Override
    public Event1Impl asEvent1()
    {
        return this;
    }
}
