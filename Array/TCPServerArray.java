import java.io.*;
import java.net.*;

public class TCPServerArray {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(9876)) {
            System.out.println("Server started. Listening for connections...");

            while (true) {
                // Accept the client connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connection accepted from client");

                // Receive the serialized array from the client
                InputStream inFromClient = clientSocket.getInputStream();
                byte[] receiveData = new byte[1024];
                inFromClient.read(receiveData);
                System.out.println("Received array from client");

                // Deserialize the array
                ObjectInputStream objectIn = new ObjectInputStream(new ByteArrayInputStream(receiveData));
                int[] array = (int[]) objectIn.readObject();
                System.out.print("Received array: [");
                for (int i = 0; i < array.length; i++) {
                    System.out.print(array[i]);
                    if (i != array.length - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println("]");

                // Find the maximum number in the array
                int max = array[0];
                for (int j = 0; j < array.length; j++) {
                    if (array[j] > max) {
                        max = array[j];
                    }
                }
                System.out.println("Max number in array: " + max);

                // Send the maximum number back to the client
                OutputStream outToClient = clientSocket.getOutputStream();
                outToClient.write(Integer.toString(max).getBytes());
                System.out.println("Sent max number to client");

                clientSocket.close();
                System.out.println("Connection closed");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
