import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClientArray {
    public static void main(String[] args) {
        try (Socket clientSocket = new Socket("localhost", 9876)) {
            // Ask the user to input an array
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter an array of integers separated by spaces: ");
            String arrayString = sc.nextLine();
            String[] arrayStrings = arrayString.split(" ");
            int[] array = new int[arrayStrings.length];
            for (int i = 0; i < arrayStrings.length; i++) {
                array[i] = Integer.parseInt(arrayStrings[i]);
            }

            // Serialize the array
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream objectOut = new ObjectOutputStream(byteOut);
            objectOut.writeObject(array);
            byte[] data = byteOut.toByteArray();

            // Send the serialized array to the server
            OutputStream outToServer = clientSocket.getOutputStream();
            outToServer.write(data);
            System.out.println("Sent array to server");

            // Receive the maximum number from the server
            InputStream inFromServer = clientSocket.getInputStream();
            byte[] receiveData = new byte[1024];
            inFromServer.read(receiveData);
            int max = Integer.parseInt(new String(receiveData).trim());
            System.out.println("Received max number from server: " + max);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
