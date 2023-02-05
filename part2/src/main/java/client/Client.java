package client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public final class Client {
    public static void main(String[] args) {
        try {
            final Socket socket = new Socket();
            socket.connect(new InetSocketAddress("localhost", 1234));
            final OutputStream outputStream = socket.getOutputStream();
            final String s = "hi";
            final byte[] data =  s.getBytes("UTF-8");
            outputStream.write(data);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
