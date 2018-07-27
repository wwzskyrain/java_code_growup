package erik.chapter01;

import java.net.*;
import java.io.*;
import java.util.*;

public class EchoClient {
    private String host = "localhost";
    private int port = 8000;
    private Socket socket;

    public EchoClient() throws IOException {

        socket = new Socket();
        socket.connect(new InetSocketAddress(port));
    }

    public static void main(String args[]) throws IOException {
        new EchoClient().talk();
    }

    private PrintWriter getWriter(Socket socket) throws IOException {
        OutputStream socketOut = socket.getOutputStream();
        return new PrintWriter(socketOut);
    }

    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream socketIn = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(socketIn));
    }

    public void talk() throws IOException {
        try {
            BufferedReader br = getReader(socket);
            PrintWriter pw = getWriter(socket);
            BufferedReader localReader = new BufferedReader(new InputStreamReader(System.in));
            String msg = null;
            while ((msg = localReader.readLine()) != null) {
                System.out.println("input:"+msg);
                pw.println(msg);
                pw.flush();
                System.out.println(br.readLine());

                if (msg.equals("bye"))
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
