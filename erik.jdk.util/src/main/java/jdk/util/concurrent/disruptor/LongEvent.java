package jdk.util.concurrent.disruptor;

/**
 * the Event that will carry the data
 */
public class LongEvent
{
    private long value;

    public void set(long value)
    {
        this.value = value;
    }
}