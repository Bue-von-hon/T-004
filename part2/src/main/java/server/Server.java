package server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public final class Server {
    private static final ExecutorService executor = Executors.newFixedThreadPool(10);
    public static void main(String[] args) {
        try(final ServerSocket serverSocket = new ServerSocket()) {
            serverSocket.bind(new InetSocketAddress("localhost", 1234));
            while (true) {
                final Socket socket = serverSocket.accept();
                final Callable callable = () -> {
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
                };
                final Future future = executor.submit(callable);
                final Object o = future.get();
                final Boolean b = (Boolean) o;
                if (Boolean.FALSE == b) {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
