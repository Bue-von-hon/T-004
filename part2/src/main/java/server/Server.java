package server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
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
                final MessageHandler handler = new MessageHandler(socket);
                final Future<Boolean> future = executor.submit(handler);
                final Boolean isEnd = future.get();
                if (Boolean.TRUE == isEnd) {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        executor.shutdown();
    }
}
