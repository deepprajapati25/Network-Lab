import java.net.*;

public class UDPClient {
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("localhost");

            // ask the user to input a string and an integer to send to the server
            System.out.print("Enter a string: ");
            byte[] sendData = new byte[1024];
            String clientString = System.console().readLine();
            System.out.print("Enter an integer: ");
            int clientInt = Integer.parseInt(System.console().readLine());
            String clientData = clientString + " " + clientInt;
            sendData = clientData.getBytes();

            // send the string and integer to the server
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket);

            // receive an integer and a string from the server
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String serverString = new String(receivePacket.getData(), 0, receivePacket.getLength());
            String[] tokens = serverString.split("\\n");
            int serverInt = Integer.parseInt(tokens[0]);
            serverString = tokens[1];
            System.out.println("From server: " + serverInt + " " + serverString);

            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
