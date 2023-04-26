import java.net.*;
import java.io.*;

public class TCPServer {
    public static void main(String args[]) {
        try {
            ServerSocket serverSocket = new ServerSocket(9876);
            System.out.println("Server started. Listening for connections...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connection accepted");

                // read the string and integer from the client
                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String clientString = inFromClient.readLine();
                int clientInt = Integer.parseInt(inFromClient.readLine());
                System.out.println("Received from client: " + clientString + " " + clientInt);

                // send back an integer and a string to the client
                int serverInt = clientInt * 2;
                String serverString = "Hello " + clientString + ", the integer you sent multiplied by 2 is: " + serverInt;
                DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());
                outToClient.writeBytes(serverInt + "\n");
                outToClient.writeBytes(serverString + "\n");

                clientSocket.close();
                System.out.println("Connection closed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
