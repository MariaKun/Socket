package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static int port = 8080;

    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("ServerSocket is created");
            Socket clientSocket = serverSocket.accept(); // ждем подключения
            System.out.println("New connection accepted");

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String name = sendQuestionAndGetAnswer(in, out, "Write your name");
            String answer = sendQuestionAndGetAnswer(in, out, "Are you child? (yes/no)");
            if (answer.equals("yes")) {
                out.println(String.format("Welcome to the kids area, %s! Let's play!", name));
            } else if (answer.equals("no")) {
                out.println(String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!", name));
            }

            out.close();
            in.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private static String sendQuestionAndGetAnswer(BufferedReader in, PrintWriter out, String message) throws IOException {
        out.println(message);
        String answer = in.readLine();
        System.out.println("Received from client " + answer);
        return answer;
    }
}