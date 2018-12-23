package jdk.util.concurrent.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

public class LongEventProducerWithTranslator {

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    private static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSLATOR =
            ((event, sequence, byteBuffer) -> event.set(byteBuffer.getLong(0)));

    /**
     * 这个方法的名字可见，是一旦数据就绪，就。。。。
     * 然而其内部实现的是"发布事件"，实现原理是-将数据翻译成事件并发布到ringBuffer中。
     *
     * @param bb
     */
    public void onData(ByteBuffer bb) {
        ringBuffer.publishEvent(TRANSLATOR, bb);
    }
}