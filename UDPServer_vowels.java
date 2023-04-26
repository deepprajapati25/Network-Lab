import java.net.*;
import java.io.*;

public class UDPServer_vowels {
  public static void main(String args[]) throws Exception {
    DatagramSocket serverSocket = new DatagramSocket(9876);
    byte[] receiveData = new byte[1024];
    byte[] sendData = new byte[1024];
	System.out.println("Connected to server");
    while (true) {
      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
      serverSocket.receive(receivePacket);
      String sentence = new String(receivePacket.getData());
      InetAddress IPAddress = receivePacket.getAddress();
      int port = receivePacket.getPort();
      int vowelCount = countVowels(sentence);
      String response = "Number of vowels: " + vowelCount;
      sendData = response.getBytes();
      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
      serverSocket.send(sendPacket);
    }
  }

  public static int countVowels(String sentence) {
    int count = 0;
    String vowels = "aeiouAEIOU";
    for (int i = 0; i < sentence.length(); i++) {
      if (vowels.indexOf(sentence.charAt(i)) != -1) {
        count++;
      }
    }
    return count;
  }
}
