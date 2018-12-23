package jdk.util.concurrent.disruptor;

import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

public class LongEventMain {
    public static void main(String[] args) throws Exception {
        // The factory for the event
        LongEventFactory factory = new LongEventFactory();

        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024;

        // Construct the Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<>(factory, bufferSize, DaemonThreadFactory.INSTANCE);

        // Connect the handler
        disruptor.handleEventsWith(new LongEventHandler());

        // Start the Disruptor, starts all threads running
        disruptor.start();

        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        LongEventProducer producer = new LongEventProducer(ringBuffer);


        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for (long l = 0; l<10; l++) {
            byteBuffer.putLong(0, l);
            producer.product(byteBuffer);    //从onData的语义上来说，producer只是处理数据；实际上这个方法应该叫做"product"
            Thread.sleep(1000);
        }

        LongEventProducerWithTranslator longEventProducerWithTranslator= new LongEventProducerWithTranslator(ringBuffer);

        for(long i = 0l;i<10;i++){
            byteBuffer.putLong(0,i);
            longEventProducerWithTranslator.onData(byteBuffer);
            TimeUnit.SECONDS.sleep(1);
        }

    }
}