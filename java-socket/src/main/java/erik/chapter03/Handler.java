package erik.chapter03;

import java.io.*;
import java.net.Socket;

class Handler implements Runnable {
    private Socket socket;

    public Handler(Socket socket) {
        this.socket = socket;
    }

    private PrintWriter getWriter(Socket socket) throws IOException {
        OutputStream socketOut = socket.getOutputStream();
        return new PrintWriter(socketOut, true);
    }

    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream socketIn = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(socketIn));
    }

    public String echo(String msg) {
        return "thread:" + Thread.currentThread().getName() + " echo:" + msg;
    }

    public void run() {
        try {

            System.out.printf("New connection accepted with address:%s and port:%d at thread:%s\n",
                    socket.getInetAddress(), socket.getPort(), Thread.currentThread().getName());
            BufferedReader br = getReader(socket);
            PrintWriter pw = getWriter(socket);

            String msg = null;
            while ((msg = br.readLine()) != null) {
                System.out.println(msg);
                pw.println(echo(msg));
                if (msg.equals("bye"))
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}