package together;

public interface Event
{
    public enum EventType {EVENT_1, EVENT_2, EVENT_3}

    public EventType getType();

    public Event1Impl asEvent1();
    public Event2Impl asEvent2();
    public Event3Impl asEvent3();
}
