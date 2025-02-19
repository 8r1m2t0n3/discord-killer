import java.net.SocketException;
import java.net.UnknownHostException;

public class Main {

  public static void main(String[] args) {

    Server server = new Server(4445);

    Client client = new Client("localhost", 4445);

    server.start();
    client.start();
  }
}
