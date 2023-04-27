import java.net.*;
import java.io.*;

public class TCPEvenOddServer {
  public static void main(String args[]) throws Exception {
    try (ServerSocket welcomeSocket = new ServerSocket(9855)) {
      System.out.println("Connected to server");
      String sentence;
      while (true) {
        Socket connectionSocket = welcomeSocket.accept();
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        System.out.println(connectionSocket.getInputStream());
        sentence = inFromClient.readLine();
        System.out.println(sentence);
        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
        String response = evenOrOdd(sentence);
        outToClient.write(response.getBytes());
        connectionSocket.close();
      }
    }
  }

  public static String evenOrOdd(String sentence) {
    int num = Integer.parseInt(sentence);
    String numStr;
    if(num %2 == 0){
        numStr = "Even number";
    }

    else{
        numStr = "Odd number";
    }

    return numStr;
}
}