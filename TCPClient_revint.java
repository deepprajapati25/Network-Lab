import java.io.*;
import java.net.*;

public class TCPClient_revint {
    public static void main(String args[]) throws Exception {
        // create input stream to read from user
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        // create client socket and connect to server
        Socket clientSocket = new Socket("localhost", 6789);
        System.out.println("Connected to server");

        // create output stream to send to server
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        // create input stream to read from server
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        // read number from user
        System.out.print("Enter a number: ");
        double number = Double.parseDouble(inFromUser.readLine());

        // send number to server
        outToServer.writeBytes(Double.toString(number) + '\n');

        // read result from server
        String serverInput = inFromServer.readLine();
        int reversedNumber = Integer.parseInt(serverInput);

        // display result
        System.out.println("Result: " + reversedNumber);

        // close connection
        clientSocket.close();
        System.out.println("Connection closed");
    }
}
