import java.net.*;
import java.io.*;
import java.util.Scanner;

public class UDPClientvow {
    public static void main(String args[]) {
        DatagramSocket clientSocket = null;
        try {
            clientSocket = new DatagramSocket();

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter a sentence: ");
            String sentence = sc.nextLine();

            byte[] sendData = sentence.getBytes();

            InetAddress IPAddress = InetAddress.getByName("localhost");
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String vowels = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Extracted vowels from server: " + vowels);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (clientSocket != null) {
                clientSocket.close();
            }
        }
    }
}
