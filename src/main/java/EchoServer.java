import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class EchoServer extends Thread {

  private final DatagramSocket socket;
  private final byte[] buf = new byte[256];

  public EchoServer(Integer port) throws SocketException {
    socket = new DatagramSocket(port);
  }

  @Override
  public void run() {
    while (true) {
      DatagramPacket packet = new DatagramPacket(buf, buf.length);
      try {
        socket.receive(packet);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }

      InetAddress address = packet.getAddress();
      int port = packet.getPort();

      String received = new String(packet.getData(), 0, packet.getLength());

      System.out.println("Received: " + received);

      if (received.contains("end")) {
        socket.close();
        continue;
      }

      packet = new DatagramPacket(received.getBytes(), received.length(), address, port);
      try {
        socket.send(packet);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
