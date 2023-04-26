import java.net.*;
import java.io.*;

public class UDPServervow {
    public static void main(String args[]) {
        DatagramSocket serverSocket = null;
        try {
            serverSocket = new DatagramSocket(9876);
            byte[] receiveData = new byte[1024];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String sentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received sentence from client: " + sentence);

                String vowels = extractVowels(sentence);
                System.out.println("Extracted vowels: " + vowels);

                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                byte[] sendData = vowels.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }

    private static String extractVowels(String sentence) {
        StringBuilder vowels = new StringBuilder();
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
                vowels.append(c);
            }
        }
        return vowels.toString();
    }
}
