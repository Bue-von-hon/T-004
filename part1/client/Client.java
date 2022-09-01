import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("localhost", 1234));
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            byte[] data = new byte[1024];
            String s = "hi";
            data =  s.getBytes("UTF-8");
            outputStream.write(data);
            inputStream.read(data);
            System.out.println(new String(data, "UTF-8")+" from server");
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
