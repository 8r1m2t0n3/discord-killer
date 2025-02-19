import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

  private final Integer port;

  public Server(Integer port) {
    this.port = port;
  }

  @Override
  public void run() {
    try {
      ServerSocket serverSocket = new ServerSocket(port);
      System.out.println("Server started. Waiting for a client...");

      Socket clientSocket = serverSocket.accept();
      System.out.println("Client connected.");

      BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

      String message = in.readLine();
      System.out.println("Client: " + message);
      out.println("Hello from server!");

      in.close();
      out.close();
      clientSocket.close();
      serverSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
