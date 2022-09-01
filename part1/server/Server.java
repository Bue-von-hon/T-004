import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("localhost", 1234));
            Socket socket = null;
            while ( (socket = serverSocket.accept()) != null) {
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                byte[] data = new byte[1024];
                int read = inputStream.read(data);
                String s = new String(data, 0, read, "UTF-8");
                System.out.println(s+" from client");
                outputStream.write(data);
            }
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
