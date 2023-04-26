import java.io.*;
import java.net.*;

public class TCPServer_revint {
    public static void main(String args[]) throws Exception {
        // create server socket
        ServerSocket welcomeSocket = new ServerSocket(6789);
        System.out.println("Server started");

        while (true) {
            // wait for client connection
            Socket connectionSocket = welcomeSocket.accept();
            System.out.println("Client connected");

            // create input stream to read from client
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

            // create output stream to send result to client
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            // read number from client
            String clientInput = inFromClient.readLine();
            double number = Double.parseDouble(clientInput);
            int integerNumber = (int) number;

            // calculate reverse
            int reversedNumber = 0;
            while (integerNumber != 0) {
                int digit = integerNumber % 10;
                reversedNumber = reversedNumber * 10 + digit;
                integerNumber /= 10;
            }

            // send result to client
            outToClient.writeBytes(Integer.toString(reversedNumber) + '\n');
            System.out.println("Result sent: " + reversedNumber);

            // close connection
            connectionSocket.close();
            System.out.println("Connection closed");
        }
    }
}
