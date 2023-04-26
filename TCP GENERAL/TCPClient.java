import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPClient {
    public static void main(String args[]) {
        try {
            Socket clientSocket = new Socket("localhost", 9876);

            // ask the user to input a string and an integer to send to the server
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter a string: ");
            String clientString = sc.nextLine();
            System.out.print("Enter an integer: ");
            int clientInt = sc.nextInt();
            sc.nextLine(); // consume the newline character

            // send the string and integer to the server
            OutputStream outToServer = clientSocket.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            out.writeBytes(clientString + "\n");
            out.writeBytes(clientInt + "\n");

            // receive an integer and a string from the server
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            int serverInt = Integer.parseInt(inFromServer.readLine());
            String serverString = inFromServer.readLine();
            System.out.println("From server: " + serverInt + " " + serverString);

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
