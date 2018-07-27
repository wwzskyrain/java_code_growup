package erik.chapter02;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class ClientTestBacklog {

    public static void main(String[] args) {

        try {
            String format = "%s connection success.";
            Socket socket1 = new Socket("localhost",8080);
            System.out.println(String.format(format,"socket1"));
            System.out.println(Thread.currentThread().getName());


            Socket socket2 = new Socket("localhost",8080);
            System.out.println(String.format(format,"socket2"));
            System.out.println(Thread.currentThread().getName());

            Socket socket3 = new Socket();

            SocketAddress socketAddress = new InetSocketAddress("localhost",8080);
            socket3.connect(socketAddress,1000);
            System.out.println(String.format(format,"socket3"));
            System.out.println(Thread.currentThread().getName());

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
