import java.net.*;
import java.io.*;

public class UDPServerArray {
    public static void main(String args[]) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(9876);
            System.out.println("Server started. Listening for connections...");

            while (true) {
                // receive the serialized array from the client
                byte[] receiveData = new byte[1024];
                byte[] sendData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                System.out.println("Received array from client");

                // deserialize the array
                ByteArrayInputStream byteIn = new ByteArrayInputStream(receiveData);
                ObjectInputStream in = new ObjectInputStream(byteIn);
                int[] array = (int[]) in.readObject();
                System.out.print("Received array: [");
                for (int i = 0; i < array.length; i++) {
                    System.out.print(array[i]);
                    if (i != array.length - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println("]");
                
                int max = array[0];
                for(int j=0;j<array.length;j++){
                    if(array[j]>max){
                        max = array[j];
                        }
                        
                }
                System.out.println(max);
                String maxi = Integer.toString(max);
                sendData = (maxi).getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
                serverSocket.send(sendPacket);
                

                System.out.println("Connection closed");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
