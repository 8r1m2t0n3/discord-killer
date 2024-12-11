import java.net.SocketException;
import java.net.UnknownHostException;

public class Main {

  public static void main(String[] args) {
    EchoServer server;
    try {
      server = new EchoServer(4445);
    } catch (SocketException e) {
      throw new RuntimeException(e);
    }

    server.start();

    EchoClient client;
    try {
      client = new EchoClient(4445);
    } catch (SocketException | UnknownHostException e) {
      throw new RuntimeException(e);
    }

    client.start();
  }
}
