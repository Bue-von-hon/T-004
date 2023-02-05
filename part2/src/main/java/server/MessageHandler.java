package server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.Callable;

public class MessageHandler implements Callable<Boolean> {
    private final Socket socket;

    public MessageHandler(Socket socket) {this.socket = socket;}

    @Override
    public Boolean call() throws Exception {
        if (!socket.isConnected()) {
            return Boolean.TRUE;
        }
        final InputStream inputStream = socket.getInputStream();
        final InputStreamReader reader = new InputStreamReader(inputStream);
        final BufferedReader in = new BufferedReader(reader);
        final String data = in.readLine();
        System.out.println(data);
        if ("exit".equals(data)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
