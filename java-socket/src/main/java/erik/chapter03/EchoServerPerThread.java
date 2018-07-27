package erik.chapter03;

import java.io.*;
import java.net.*;

public class EchoServerPerThread {
    private int port = 8000;
    private ServerSocket serverSocket;

    private int counter = 0;

    public EchoServerPerThread() throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("服务器启动");
    }

    public void service() {
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();  //接收客户连接
                System.out.printf("accept a new socket at %s thread\n", Thread.currentThread().getName());
                Thread workThread = new Thread(new Handler(socket));  //创建一个工作线程
                workThread.start();  //启动工作线程
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) throws IOException {
        new EchoServerThreadPool().service();
    }
}




/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
