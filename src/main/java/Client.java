import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client extends Thread {

  private final String host;
  private final Integer port;

  public Client(String host, Integer port) {
    this.host = host;
    this.port = port;
  }

  @Override
  public void run() {
    try {
      Socket socket = new Socket(host, port);
      System.out.println("Connected to server.");

      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

      out.println("Hello from client!");
      String response = in.readLine();
      System.out.println("Server: " + response);

      in.close();
      out.close();
      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
