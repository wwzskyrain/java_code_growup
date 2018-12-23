package jdk.util.concurrent.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * to preallocate these events, aka to perform the construction.
 */
public class LongEventFactory implements EventFactory<LongEvent>
{
    public LongEvent newInstance()
    {
        return new LongEvent();
    }
}