package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket clientSocket = new Socket("localhost", Main.port)) {
            System.out.println("ClientSocket is created");
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Scanner scanner = new Scanner(System.in);

            getQuestionAndSendAnswer(in, out, scanner);
            getQuestionAndSendAnswer(in, out, scanner);
            System.out.println("Received from server " + in.readLine());

            scanner.close();
            out.close();
            in.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private static void getQuestionAndSendAnswer(BufferedReader reader, PrintWriter writer, Scanner scanner) throws IOException {
        System.out.println("Received from server " + reader.readLine());
        String answer = scanner.nextLine();
        writer.println(answer);
    }
}
