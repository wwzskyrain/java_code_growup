package erik.chapter03;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class EchoServerThreadPoolJdk {
    private int port = 8000;
    private ServerSocket serverSocket;
    private ExecutorService executorService;  //线程池
    private final int POOL_SIZE = 4;  //单个CPU时线程池中工作线程的数目

    public EchoServerThreadPoolJdk() throws IOException {
        serverSocket = new ServerSocket(port);
        //创建线程池
        //Runtime的availableProcessors()方法返回当前系统的CPU的数目
        //系统的CPU越多，线程池中工作线程的数目也越多
        executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors() * POOL_SIZE);

        System.out.println("服务器启动");
    }

    public void service() {
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                executorService.execute(new Handler(socket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) throws IOException {
        new EchoServerThreadPoolJdk().service();
    }
}