import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(9876);
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            System.out.println("Server started. Listening for connections...");

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                System.out.println("Connection accepted");

                // read the string and integer from the client
                String clientString = new String(receivePacket.getData(), 0, receivePacket.getLength());
                int clientInt = Integer.parseInt(clientString.replaceAll("[^0-9]+", "")); // extract only digits
                System.out.println("Received from client: " + clientString + " " + clientInt);

                // send back an integer and a string to the client
                int serverInt = clientInt * 2;
                String serverString = "Hello " + clientString + ", the integer you sent multiplied by 2 is: " + serverInt;
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                sendData = (serverInt + "\n" + serverString).getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);

                System.out.println("Connection closed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
