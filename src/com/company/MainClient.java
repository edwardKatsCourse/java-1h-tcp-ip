package com.company;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MainClient {

    public static void main(String[] args) throws IOException {

        InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
        Socket socket = new Socket(inetAddress, 9000);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream()
                )
        );

        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        socket.getOutputStream()
                )
        );

        while (true) {
            System.out.println("Message to server:");
            String line = new Scanner(System.in).nextLine();

            if (line.startsWith("exit")) {
                break;
            }

            writer.write(line + "\n");
            writer.flush();

            System.out.println("Server message: ");
            String serverMessage = reader.readLine();
            System.out.println(serverMessage);
        }

        writer.close();
        reader.close();
        socket.close();
    }
}
