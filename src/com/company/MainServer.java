package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MainServer {

    /**
     * 1. Network types
     * 2. Packet structure (by OSI)
     * 3. TCP/IP
     * 4. Java view
     *
     */
    public static void main(String[] args) throws IOException {

//        InetAddress inetAddress = InetAddress.getByName("facebook.com");

        //  TCP/IP
        //java.net.ServerSocket -> InputStream
        //java.net.Socket       -> OutputStream

        //OSI layers         1, 2                                   3, 4
        //JVM       ->  Operation System (Windows, Mac) -> Network Interface Card (NIC) -> Wifi -> City router

        //InputStream -> reads data
        ServerSocket serverSocket = new ServerSocket(9000);

        //OutputStream -> sends data
        Socket socket = serverSocket.accept();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream()
                ));

        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        socket.getOutputStream()
                )
        );

        while (true) {
            System.out.println("Message from client:");
            String line = reader.readLine();
            if (line.startsWith("exit")) {
                System.out.println("Quit");
                break;
            }

            System.out.println(line);


            System.out.println("Enter server response");
            String response = new Scanner(System.in).nextLine();

            writer.write(response + "\n");
            writer.flush();
        }

        reader.close();
        writer.close();
        socket.close();
        serverSocket.close();
    }
}
