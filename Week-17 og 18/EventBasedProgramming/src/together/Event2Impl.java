package together;

public class Event2Impl extends AbstractEvent
{
    private final String param;

    public Event2Impl(String param)
    {
        this.param = param;
    }

    public String getParam()
    {
        return param;
    }

    @Override
    public EventType getType()
    {
        return EventType.EVENT_2;
    }

    @Override
    public Event2Impl asEvent2()
    {
        return this;
    }
}
