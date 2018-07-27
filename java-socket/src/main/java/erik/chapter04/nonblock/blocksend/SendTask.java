package erik.chapter04.nonblock.blocksend;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.BlockingQueue;

public class SendTask implements Runnable {

    private BlockingQueue<SocketChannel> blockingQueue;

    private Charset charset = Charset.forName("GBK");

    SendTask(BlockingQueue blockingQueue) {

        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {

        try {
            while (true) {

                SocketChannel socketChannel = blockingQueue.take(); //阻塞在这里

                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int position = buffer.position();
                int nowPosition = socketChannel.read(buffer);
                if(nowPosition==position)
                    continue;
                buffer.flip();
                CharBuffer charBuffer = charset.decode(buffer);

                System.out.println("receive:" + charBuffer.toString());
                ByteBuffer buffer1 = charset.encode("echo:" + charBuffer.toString());
                socketChannel.write(buffer1);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
