package erik.chapter04.nonblock.blocksend;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class EchoServer {

    private Selector selector = null;

    ServerSocketChannel serverSocketChannel = null;

    BlockingQueue blockingQueue;

    private Charset charset = Charset.forName("GBK");

    public EchoServer(BlockingQueue blockingQueue) throws IOException {

        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();

        InetSocketAddress socketAddress = new InetSocketAddress(8000);
        serverSocketChannel.socket().setReuseAddress(true);
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(socketAddress);
        this.blockingQueue = blockingQueue;
        System.out.printf("Server start at %s:%d\n", socketAddress.getHostName(), socketAddress.getPort());

    }

    public void service() throws IOException {

        System.out.println("service()...");

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {

            Set<SelectionKey> keySet = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if (selectionKey.isAcceptable()) {

                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();

                    InetSocketAddress remoteAddress = (InetSocketAddress) socketChannel.getRemoteAddress();

                    System.out.printf("receive connection from %s:%d at %s\n",
                            remoteAddress.getHostName(), remoteAddress.getPort(), Thread.currentThread().getName());

                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
//                  不监听"可写"事件
                }
                if (selectionKey.isReadable()) {

                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    InetSocketAddress socketAddress = (InetSocketAddress) socketChannel.getRemoteAddress();
//              客户端发一次数据，
                    System.out.printf("channel %s:%d is readable.\n",socketAddress.getHostName(),socketAddress.getPort());
                    try {
                        blockingQueue.put(socketChannel);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }


    }


    public static void main(String[] args) throws IOException {

        BlockingQueue<SocketChannel> socketChannels = new LinkedBlockingDeque<>();

        EchoServer echoServer = new EchoServer(socketChannels);

        Thread thread = new Thread(new SendTask(socketChannels));
        thread.setDaemon(true);
        thread.start();

        echoServer.service();

    }


}
