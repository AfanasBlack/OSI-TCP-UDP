import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private static int PORT = 8080;
    private static String ip = "localhost";

    public static void main(String[] args) {
        try (Socket clientSocket = new Socket(ip, PORT);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));){

            out.println("Alexandr");

            String serverResult = in.readLine();

            System.out.println("Server response: " + serverResult);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
