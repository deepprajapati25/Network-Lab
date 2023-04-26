import java.net.*;
import java.io.*;
import java.util.Scanner;

public class UDPClientArray {
    public static void main(String args[]) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();

            // ask the user to input an array
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter an array of integers separated by spaces: ");
            String arrayString = sc.nextLine();
            String[] arrayStrings = arrayString.split(" ");
            int[] array = new int[arrayStrings.length];
            for (int i = 0; i < arrayStrings.length; i++) {
                array[i] = Integer.parseInt(arrayStrings[i]);
            }

            // serialize the array
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(array);
            byte[] data = byteOut.toByteArray();

            // send the serialized array to the server
            InetAddress IPAddress = InetAddress.getByName("localhost");
            int port = 9876;
            DatagramPacket sendPacket = new DatagramPacket(data, data.length, IPAddress, port);
            clientSocket.send(sendPacket);
            System.out.println("Sent array to server");



            // receive the maximum number from the server
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String maxString = new String(receivePacket.getData(), 0, receivePacket.getLength());
            int max = Integer.parseInt(maxString);
            System.out.println("Received max number from server: " + max);

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
