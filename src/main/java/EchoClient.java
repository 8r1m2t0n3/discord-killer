import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class EchoClient extends Thread {

  private static final String IP = "79.101.158.52";

  private final DatagramSocket socket;
  private final InetAddress address;
  private final Integer port;

  private byte[] buf;

  public EchoClient(Integer port) throws SocketException, UnknownHostException {
    socket = new DatagramSocket();
    address = InetAddress.getByName(IP);
    this.port = port;
  }

  public String sendEcho(String msg) throws IOException {
    buf = msg.getBytes();
    DatagramPacket packet
        = new DatagramPacket(buf, buf.length, address, port);
    socket.send(packet);
    packet = new DatagramPacket(buf, buf.length);
    socket.receive(packet);
    return new String(packet.getData(), 0, packet.getLength());
  }

  public void close() {
    socket.close();
  }

  @Override
  public void run() {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.print("Enter line: ");
      String line = scanner.nextLine();

      if (line.equals("end")) {
        close();
        return;
      }

      try {
        sendEcho(line);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
