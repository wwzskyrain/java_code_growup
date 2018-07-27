package erik.chapter02;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.TimeUnit;

public class ServerSetWaitingQueueSize {

    public static void main(String[] args) {

        try {

            ServerSocket serverSocket = new ServerSocket(8080,2);
            System.out.println("new ServerSocket()");
            TimeUnit.SECONDS.sleep(3600);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
