import java.io.*;
import java.net.*;
import java.lang.Math.*;
public class TCPServer {
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

            // calculate square root
            double result = Math.sqrt(number);

            // send result to client
            outToClient.writeBytes(Double.toString(result) + '\n');
            System.out.println("Result sent: " + result);

            // close connection
            connectionSocket.close();
            System.out.println("Connection closed");
        }
    }
}
