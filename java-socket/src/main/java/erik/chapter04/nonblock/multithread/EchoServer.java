package erik.chapter04.nonblock.multithread;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.net.*;
import java.util.*;

public class EchoServer {
//  主线程 - accepted线程
//  主线程用来读写，accepted线程用来接受链接；
    private Selector selector = null;
    private ServerSocketChannel serverSocketChannel = null;
    private int port = 8000;
    private Charset charset = Charset.forName("GBK");

    public EchoServer() throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().setReuseAddress(true);
        // 一个阻塞的serverSocketChannel
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        System.out.println("服务器启动");
    }

    public void accept() {
        for (; ; ) {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                System.out.println("接收到客户连接，来自:" +
                        socketChannel.socket().getInetAddress() +
                        ":" + socketChannel.socket().getPort());

                socketChannel.configureBlocking(false);
//              与客户端的链接socketChannel都设置成"非阻塞"

                ByteBuffer buffer = ByteBuffer.allocate(1024);
                synchronized (gate) {
                    selector.wakeup();
                    socketChannel.register(selector,
                            SelectionKey.OP_READ |
                                    SelectionKey.OP_WRITE, buffer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Object gate = new Object();

    public void service() throws IOException {
        for (; ; ) {
            synchronized (gate) {
            }
            int n = selector.select();
            //当被selector.wakeup后，发现n=0，然后在进入for循环，发现gate的synchronized要等
//          accept线程中的gate锁；
            if (n == 0) continue;
            Set readyKeys = selector.selectedKeys();
            Iterator it = readyKeys.iterator();
            while (it.hasNext()) {
                SelectionKey key = null;
                try {
                    key = (SelectionKey) it.next();
                    it.remove();
                    if (key.isReadable()) {
                        receive(key);
                    }
                    if (key.isWritable()) {
//                      这个会一直都是"可写的"，所以这个会一直都被调用
                        send(key);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    try {
                        if (key != null) {
                            key.cancel();
                            key.channel().close();
                        }
                    } catch (Exception ex) {
                        e.printStackTrace();
                    }
                }
            }//#while
        }//#while
    }

    public void send(SelectionKey key) throws IOException {
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        SocketChannel socketChannel = (SocketChannel) key.channel();
        buffer.flip();  //把极限设为位置
        String data = decode(buffer);

        InetSocketAddress remoteAddress = (InetSocketAddress) socketChannel.getRemoteAddress();

        if (data.indexOf("\n") == -1) {
            System.out.printf("no data to send to %s:%d at thread:%s\n",
                    remoteAddress.getHostName(), remoteAddress.getPort(), Thread.currentThread().getName());
            return;
        }
        String outputData = data.substring(0, data.indexOf("\n") + 1);
        System.out.print(outputData);
        ByteBuffer outputBuffer = encode("echo:" + outputData);
        while (outputBuffer.hasRemaining())
            socketChannel.write(outputBuffer);

        ByteBuffer temp = encode(outputData);
        buffer.position(temp.limit());
        buffer.compact();

        if (outputData.equals("bye\r\n")) {
            key.cancel();
            socketChannel.close();
            System.out.println("关闭与客户的连接");
        }
    }

    public void receive(SelectionKey key) throws IOException {
        ByteBuffer buffer = (ByteBuffer) key.attachment();

        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer readBuff = ByteBuffer.allocate(32);
        socketChannel.read(readBuff);
        readBuff.flip();

        buffer.limit(buffer.capacity());
        buffer.put(readBuff);
    }

    public String decode(ByteBuffer buffer) {  //解码
        CharBuffer charBuffer = charset.decode(buffer);
        return charBuffer.toString();
    }

    public ByteBuffer encode(String str) {  //编码
        return charset.encode(str);
    }

    public static void main(String args[]) throws Exception {
        final EchoServer server = new EchoServer();
        Thread accept = new Thread() {
            public void run() {
                server.accept();
            }
        };
        accept.start();
        server.service();
    }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
