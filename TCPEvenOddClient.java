import java.io.*;
import java.net.*;

public class TCPEvenOddClient {
  public static void main(String args[]) throws Exception {
    BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
    Socket clientSocket = new Socket("localhost", 9855);
    DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
    BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    System.out.println("Enter a number to check if it is even or not");
    String sentence = inFromUser.readLine();
    outToServer.writeBytes(sentence + "\n");
    String response = inFromServer.readLine();
    System.out.println("From server: " + response);
    clientSocket.close();
    System.out.println("connection terminated");
  }
}