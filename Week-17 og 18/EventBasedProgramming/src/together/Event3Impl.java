package together;

public class Event3Impl extends AbstractEvent
{
    private final String param1;
    private final String param2;

    public Event3Impl(String param1, String param2)
    {
        this.param1 = param1;
        this.param2 = param2;
    }

    public String getParam1()
    {
        return param1;
    }

    public String getParam2()
    {
        return param2;
    }

    @Override
    public EventType getType()
    {
        return EventType.EVENT_3;
    }

    @Override
    public Event3Impl asEvent3()
    {
        return this;
    }
}
