package jdk.util.concurrent.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

public class LongEventProducer {
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }


    //方法名命名不好，不符合上下语义，应该该做'product'。
    public void product(ByteBuffer bb) {
        long sequence = ringBuffer.next();  // Grab the next sequence,aka claim a slot in the Ring Buffer
        try {
            LongEvent event = ringBuffer.get(sequence); // Get the entry in the Disruptor
            // for the sequence
            event.set(bb.getLong(0));  // Fill with data
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}