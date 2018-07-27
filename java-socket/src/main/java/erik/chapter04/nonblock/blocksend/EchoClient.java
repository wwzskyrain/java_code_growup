package erik.chapter04.nonblock.blocksend;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class EchoClient {

    Socket socket = null;

    public EchoClient() throws IOException {

        socket = new Socket();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8000);
        socket.connect(inetSocketAddress);
        System.out.printf("connect to %s:%d\n", inetSocketAddress.getHostName(), inetSocketAddress.getPort());

    }

    private BufferedReader getReader(Socket socket) throws IOException {

        InputStream inputStream = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(inputStream));

    }

    private PrintWriter getPrintWriter(Socket socket) throws IOException {

        OutputStream outputStream = socket.getOutputStream();
        return new PrintWriter(new OutputStreamWriter(outputStream), true);
    }

    private String getInputFromUserCommandLine() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line = bufferedReader.readLine();
        return line;

    }

    public void talk() throws IOException {

        BufferedReader reader = getReader(socket);
        PrintWriter printWriter = getPrintWriter(socket);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String msg = null;
        while ((msg = bufferedReader.readLine()) != null) {

            printWriter.printf(msg);
            printWriter.flush();
            System.out.printf("talk:%s waiting return...\n", msg);
            System.out.println(reader.readLine());
            if ("bye".equals(msg)) {
                break;
            }

        }
    }

    public static void main(String[] args) throws IOException {

        new EchoClient().talk();

    }
}

