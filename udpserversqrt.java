import java.io.*;
import java.net.*;  
import java.lang.Math.*;
class udpserversqrt
{
    public static void main(String args[]) throws Exception
    {
        DatagramSocket serverSocket=new DatagramSocket(2525);
        byte[] receiveData=new byte[1024]; 
        byte[] sendData=new byte[1024];
        while(true)
        {
            DatagramPacket receivePacket=new DatagramPacket(receiveData,receiveData.length);
            serverSocket.receive(receivePacket);

            String sentence=new String(receivePacket.getData());
            System.out.println("Received "+sentence);
            InetAddress IPAddress=receivePacket.getAddress();
            int port=receivePacket.getPort();
            double num=Double.parseDouble(sentence);
            double result=Math.sqrt(num);
            sendData=Double.toString(result).getBytes();
            DatagramPacket sendPacket=new DatagramPacket(sendData,sendData.length,IPAddress,port);
            serverSocket.send(sendPacket);
        }
    }
}